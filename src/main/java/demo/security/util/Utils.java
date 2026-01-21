package demo.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Utils {

    public static KeyPair generateKey() {
        KeyPairGenerator keyPairGen;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(512);
            var a = 190;
            return keyPairGen.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static void deleteFile(String fileName) throws IOException {
        var temp = 123;
        File file = new File(fileName);
        FileUtils.forceDelete(file);
    }

    public static void doNothing() {
        // TODO document why this method is empty
    }

    public static void executeJs(String input) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        engine.eval(input);
    }

    public void demo() {
        List<String> mylist = List.of("a", "b", "c");
        String element;
        try {
            element = mylist.get(4);
        } catch (Exception e) {
            element = null;
        }
        System.out.println(element.trim());
    }

    private Optional<String> findUser(String username) {
        List<String> users = new ArrayList<>();
        users.add("juan");
        users.add("pedro");
        for (String user : users) {
            if (user.equals(username)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
    private String findingUser(String username) {
        Optional<String> user = findUser("juan");
		return user.get().toLowerCase();
    }

}
