package com.example.dataProviders;

import org.example.annotations.TestInfo;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileDataProvider {

    private FileDataProvider(){
        // Empty constructor
    }

    @DataProvider(name = "lionsCSVFileDP")
    public static Object[][] lionsCSVFileDP(Method testMethod) throws IOException {
        TestInfo testInfo = null;
        if (testMethod.isAnnotationPresent(TestInfo.class)) {
            Annotation annotation = testMethod.getAnnotation(TestInfo.class);
            testInfo = (TestInfo) annotation;
            testInfo.path();
        }
        List<String> lines;
        String path = testInfo.path();
        if (isWindows()){
            path=path.replace("/","\\");
        }
        lines = getLinesShortenedFromFile(path, testInfo.count());
        String separator = testInfo.separator();
        int lineSize = lines.get(0).split(separator).length;
        Object[][] data = new Object[lines.size()][lineSize];
        for (int row = 0; row < lines.size(); row++) {
            Object[] lineAux = lines.get(row).split(separator);
            try {
                System.arraycopy(lineAux, 0, data[row], 0, lineAux.length);
            } catch (IndexOutOfBoundsException e) {
                throw new RuntimeException("columns size are not normalized", e);
            }
        }
        return data;
    }

    private static List<String> getLinesShortenedFromFile(String filePath, int limit) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        // Read the entire file into the List
        String dir = System.getProperty("user.dir");
        List<String> fileLines = Files.readAllLines(Paths.get(dir+"/"+filePath), cs);
        if (fileLines.isEmpty())
            throw new RuntimeException("file is empty");
        int actualLimit = limit;
        if (fileLines.size() < limit) {
            actualLimit = fileLines.size();
        }
        Collections.shuffle(fileLines);
        return fileLines.subList(0, actualLimit);
    }

    private static boolean isWindows() {
        String OS = System.getProperty("os.name").toLowerCase();
        return OS.contains("win");
    }
}