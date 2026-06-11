package smartlostfound.model.repository;

import smartlostfound.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository {
    private static final String USERS = "users.txt";
    private static final String LOST = "lost_reports.txt";
    private static final String FOUND = "found_reports.txt";
    private static final String MATCHES = "matches.txt";
    private static final String NOTIFS = "notifications.txt";

    public void initializeFiles() {
        createFile(USERS);
        createFile(LOST);
        createFile(FOUND);
        createFile(MATCHES);
        createFile(NOTIFS);
    }

    private void createFile(String name) {
        try {
            File file = new File(name);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException ignored) {
        }
    }

    public User findUserById(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 3 && parts[0].equals(id)) {
                    return new User(parts[0], parts[1], parts[2]);
                }
            }
        } catch (IOException ignored) {
        }
        return null;
    }

    public void saveUser(User user) {
        append(USERS, user.toFileString());
    }

    public List<LostReport> getLostReports() {
        List<LostReport> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOST))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length < 7) continue;
                LostItem item = new LostItem("I", parts[2], parts[3], parts[4], parts[5]);
                list.add(new LostReport(parts[0], parts[1], item, parts[6]));
            }
        } catch (IOException ignored) {
        }
        return list;
    }

    public void saveLostReport(LostReport report) {
        append(LOST, report.toFileString());
    }

    public List<FoundReport> getFoundReports() {
        List<FoundReport> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FOUND))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length < 7) continue;
                FoundItem item = new FoundItem("I", parts[2], parts[3], parts[4], parts[5]);
                list.add(new FoundReport(parts[0], parts[1], item, parts[6]));
            }
        } catch (IOException ignored) {
        }
        return list;
    }

    public void saveFoundReport(FoundReport report) {
        append(FOUND, report.toFileString());
    }

    public List<Match> getAllMatches() {
        List<Match> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MATCHES))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length < 5) continue;
                list.add(new Match(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]), parts[4]));
            }
        } catch (IOException ignored) {
        }
        return list;
    }

    public void saveMatch(Match match) {
        append(MATCHES, match.toFileString());
    }

    public List<Notification> getNotificationsForUser(String userId) {
        List<Notification> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTIFS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 4 && parts[1].equals(userId)) {
                    list.add(new Notification(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException ignored) {
        }
        return list;
    }

    public void saveNotification(Notification notification) {
        append(NOTIFS, notification.toFileString());
    }

    private void append(String filename, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException ignored) {
        }
    }
}
