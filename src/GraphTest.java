import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GraphTest {


//
    @Test//有效等价类1：句子中有一对词有桥接词，这对词总共有一个桥接词
    public void testGenerateNewText_1_1() {
        Lab1 obj = new Lab1();
        graph G=obj.makegraph();// Arrange
        String inputText = "its female flowers";
        String expectedOutput = "its female gather flowers"; // Example expected output

        // Act
        String actualOutput = G.generateNewText(inputText);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

   @Test//有效等价类2：句子中有一对词有桥接词，这对词有多个桥接词
    public void testGenerateNewText_1_n() {
       Lab1 obj = new Lab1();
       graph G=obj.makegraph();// Arrange
       String inputText = "little bees";
       String expectedOutput1 = "little bumble bees"; // Example expected output
       String expectedOutput2 = "little beautiful bees"; // Example expected output
        // Act
       String actualOutput = G.generateNewText(inputText);

        // Assert
        assertTrue(actualOutput.equals(expectedOutput1)||actualOutput.equals(expectedOutput2));
    }

    @Test//有效等价类3：句子中没有桥接词
    public void testGenerateNewText_1_0() {
        Lab1 obj = new Lab1();
        graph G=obj.makegraph();// Arrange
        String inputText = "insect welfare";

        // Act
        String actualOutput = G.generateNewText(inputText);

        // Assert
        assertEquals(inputText, actualOutput);
    }

    @Test//有效等价类4：句子中有多词有桥接词
    public void testGenerateNewText_n() {
        Lab1 obj = new Lab1();
        graph G=obj.makegraph();// Arrange
        String inputText = "bees over flowers";
        String expectedOutput = "bees flying over the flowers"; // Example expected output
        // Act
        String actualOutput = G.generateNewText(inputText);

        // Assert
        assertEquals(actualOutput,expectedOutput);
    }



    @Test//无效等价类1：空字符串
    public void testGenerateNewText_null() {
        Lab1 obj = new Lab1();
        graph G=obj.makegraph();// Arrange
        String inputText = "";
        String expectedOutput = ""; // Example expected output
        // Act
        String actualOutput = G.generateNewText(inputText);

        // Assert
        assertEquals(actualOutput,expectedOutput);
    }



    @Test//边界情况1：句子只有一个单词
    public void testGenerateNewText_only() {
        Lab1 obj = new Lab1();
        graph G=obj.makegraph();// Arrange
        String inputText = "welfare";

        // Act
        String actualOutput = G.generateNewText(inputText);

        // Assert
        assertEquals(inputText, actualOutput);
    }
    }
