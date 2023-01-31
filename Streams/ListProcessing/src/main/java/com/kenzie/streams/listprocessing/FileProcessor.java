package com.kenzie.streams.listprocessing;

import com.kenzie.streams.listprocessing.resources.ProjectServerManager;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FileProcessor {

    private ProjectServerManager serverManager;

    /**
     * Constructor for FileProcessor.
     */
    public FileProcessor() {
        serverManager = new ProjectServerManager();
    }

    /**
     * Returns a {@code `List<String>`} that contains only the file names for .txt or .md files, all lowercase and
     * sorted alphabetically.
     * PARTICIPANTS: Complete this method.
     * @param source Source list.
     * @return Processed list.
     */
    public List<String> filterDocs(List<String> source) {
        if (source == null || source.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> result = source.stream()
                .filter(s -> s.toLowerCase().endsWith(".txt") || s.toLowerCase().endsWith(".md"))
                .map(String::toLowerCase)
                .sorted()
                .collect(Collectors.toList());
        return result;
    }

    /**
     * Returns a {@code `Set<String>`} that contains only the file names for .java files, with each file capitalized.
     * PARTICIPANTS: Complete this method.
     * @param source Source List.
     * @return Processed Set.
     */
    public Set<String> filterJava(List<String> source) {
        if (source == null || source.isEmpty()) {
            return Collections.emptySet();
        }

        Set<String> result = source.stream()
                .filter(s -> s.endsWith(".java"))
                .map(s -> {
                    String fileName = s.substring(0, s.lastIndexOf('.'));
                    return fileName.substring(0,1).toUpperCase() + fileName.substring(1) + ".java";
                })
                .collect(Collectors.toSet());
        return result;
    }

    /**
     * Sorts all file names in the list, and submits them in order to the project server via the method
     * `submitToProject()` of the `ProjectServerManager` class.
     * PARTICIPANTS: Complete this method.
     * @param source Source list.
     */
    public void sortAndSubmitAll(List<String> source) {
        List<String> sortedFiles = source.stream()
                .sorted()
                .collect(Collectors.toList());
        for (String fileName : sortedFiles) {
            ProjectServerManager.submitToProject(fileName);
        }
    }
}
