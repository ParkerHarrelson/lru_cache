package org.parkerharrelson;

import java.util.Scanner;

import org.parkerharrelson.cache.LRUCache;

public class Main {

    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache<>(5);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean isOn = true;

            printWelcome();

            while (isOn) {
                System.out.print("> ");

                if (!scanner.hasNextLine()) {
                    break;
                }

                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    continue;
                }

                String[] inputs = input.split("\\s+", 3);
                String command = inputs[0].toLowerCase();

                switch (command) {
                    case "put":
                    case "update":
                        if (inputs.length < 3) {
                            System.out.println("Usage: put <integerKey> <value>");
                            break;
                        }

                        try {
                            int key = Integer.parseInt(inputs[1]);
                            String value = inputs[2];
                            lruCache.put(key, value);
                            System.out.println("Stored: " + key + " -> " + value);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid key. Key must be an integer.");
                        }
                        break;

                    case "get":
                        if (inputs.length < 2) {
                            System.out.println("Usage: get <integerKey>");
                            break;
                        }

                        try {
                            int key = Integer.parseInt(inputs[1]);
                            String value = lruCache.get(key);

                            if (value == null) {
                                System.out.println("Key not found: " + key);
                            } else {
                                System.out.println(value);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid key. Key must be an integer.");
                        }
                        break;

                    case "evict":
                        if (inputs.length < 2) {
                            System.out.println("Usage: evict <key>");
                            break;
                        }

                        try {
                            int key = Integer.parseInt(inputs[1]);
                            boolean removed = lruCache.evict(key);

                            if (removed) {
                                System.out.println("Evicted key " + key);
                            } else {
                                System.out.println("Key not found: " + key);
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Invalid key. Key must be an integer.");
                        }
                        break;

                    case "clear":
                        lruCache.clear();
                        System.out.println("Cache cleared.");
                        break;

                    case "dump":
                        System.out.println("Cache contents (most recent to least recent):");
                        lruCache.printInOrder();
                        break;

                    case "size":
                        System.out.println("Current size: " + lruCache.getCurrentSize());
                        break;

                    case "help":
                        printHelp();
                        break;

                    case "exit":
                    case "quit":
                        isOn = false;
                        System.out.println("Exiting.");
                        break;

                    default:
                        System.out.println("Unknown command: " + command);
                        printHelp();
                        break;
                }
            }
        }
    }

    private static void printWelcome() {
        System.out.println("LRU Cache Console");
        printHelp();
    }

    private static void printHelp() {
        System.out.println("Commands:");
        System.out.println("  put <key> <value>     - Insert or update a value");
        System.out.println("  update <key> <value>  - Alias for put");
        System.out.println("  get <key>             - Retrieve a value");
        System.out.println("  evict <key>           - Remove a specific key from the cache");
        System.out.println("  clear                 - Remove all entries from the cache");
        System.out.println("  dump                  - Print cache in order");
        System.out.println("  size                  - Print current cache size");
        System.out.println("  help                  - Show commands");
        System.out.println("  exit                  - Quit");
    }
}