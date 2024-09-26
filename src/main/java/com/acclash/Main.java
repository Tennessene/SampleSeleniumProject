package com.acclash;

import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    private static final AtomicBoolean passwordFound = new AtomicBoolean(false);

    static String email;

    static int threads;

    static int runner;

    static int totalRunners;

    static boolean parallel;

    public static void main(String[] args) {
        if (!checkDriver()) {
            System.out.println("Could not find chromeDriver!");
        }
    }

    private static boolean checkDriver() {
        String os = System.getProperty("os.name").toLowerCase();
        os = os.toLowerCase();
        String driverProperty = "webdriver.chrome.driver";
        if (isRunningInGitHubActions()) {
            System.out.println("Running on GitHub Actions!");
            String driverDir = "/usr/local/share/chromedriver-linux64/chromedriver";
            System.setProperty(driverProperty, driverDir);

            ChromeDriver tDriver = new ChromeDriver();
            String chromeDriverVersion = tDriver.getCapabilities().getBrowserVersion();
            System.out.println("Using built-in chromeDriver version " + chromeDriverVersion);
            tDriver.quit();
        } else {
            if (os.contains("windows")) {
                String driverDir = "chromedriver-win64/chromedriver.exe";
                System.setProperty(driverProperty, driverDir);
            } else if (os.contains("mac")) {
                String driverDir = "chromedriver-mac-x64/chromedriver";
                System.setProperty(driverProperty, driverDir);
            } else if (os.contains("linux")) {
                String driverDir = "chromedriver-linux64/chromedriver";
                System.setProperty(driverProperty, driverDir);
            } else {
                System.out.println("System not supported. Sorry!");
                return false;
            }
            ChromeDriver tDriver = new ChromeDriver();
            String chromeDriverVersion = tDriver.getCapabilities().getBrowserVersion();
            System.out.println("Using provided chromeDriver version " + chromeDriverVersion);
            tDriver.quit();
        }

        return new File(System.getProperty("webdriver.chrome.driver")).exists();
    }

    private static boolean isRunningInGitHubActions() {
        String githubActionsEnv = System.getenv("GITHUB_ACTIONS");
        return githubActionsEnv != null && githubActionsEnv.equals("true");
    }
}