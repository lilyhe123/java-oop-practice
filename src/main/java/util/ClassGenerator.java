package util;

import java.io.*;

/**
 * Based on the info in the input file, generate class and test java file.
 * In the input file,
 * the class name appears in the first line, followed by the question description.
 * Generate the java class file with the class name
 * and add the description as the class comment with each line less than 80.
 */
public class ClassGenerator {
  private String inputFile = "question.txt";
  private String rootDir = System.getProperty("user.dir");
  private String javaSuffix = ".java";
  private String[] classTokens = {"src", "main", "java", "oop"};
  private String[] testTokens = {"src", "test", "java", "oop"};
  private int columnLimit = 80;
 
  public static void main(String... args) {
    ClassGenerator generator = new ClassGenerator();
    generator.parseAndGenerate();
  }
  private String generatePathByTokens(String[] tokens) {
    StringBuilder sb = new StringBuilder();
    for (String token : tokens) {
      sb.append(token).append(File.separator);
    }
    return sb.toString();
  }
  private void parseAndGenerate() {
    // read input file from resource folder
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(
        getClass().getClassLoader().getResourceAsStream(inputFile)))) {
      
      String className;
      while((className = reader.readLine()) != null && className.trim().isEmpty());
      String classFile = rootDir + File.separator + 
        generatePathByTokens(classTokens) + className + javaSuffix;
      if (new File(classFile).exists()) {
        System.out.println("The class file " + className + " already exists. Do nothing.");
        return;
      }
      String testClassName = className + "Test";
      String testFile = rootDir + File.separator + 
        generatePathByTokens(testTokens) + testClassName + javaSuffix;
      
      generateClassFile(reader, className, classFile);
      //generateTestFile(testClassName, testFile);
      System.out.println("Class " + className + " has been generated.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private void generateClassFile(BufferedReader reader, String className, String outputFile) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
      writer.write("package oop;\n\n/**\n");
      String prefix = " * ";
      String line;
      while((line = reader.readLine()) != null) {
        writer.write(prefix);
        int len = prefix.length();
        String[] tokens = line.split(" ");
        for(String token : tokens) {
          if(len + token.length() > columnLimit) {
            // start a new line
            writer.write("\n");
            writer.write(prefix);
            len = prefix.length();
          }
          writer.write(token + " ");
          len += token.length()+1;
        }
        writer.write("\n");
      }
      writer.write(prefix + "-----------------------------\n" + prefix + "\n");
      writer.write(" */\n\n");

      writer.write("public class " + className + " {\n");
      //writer.write("    // TODO: Add fields, methods, and logic here\n");
      writer.write("    public " + className + "() {\n");
      writer.write("        // Default constructor\n");
      writer.write("    }\n");
      writer.write("}\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  private void generateTestFile(String className, String outputFile) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
      writer.write("package oop;\n" + //
                "\n" + //
                "import org.junit.jupiter.api.Test;\n" + //
                "import static org.junit.jupiter.api.Assertions.*;\n\n" +
                "public class " + className + "{\n}\n");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
