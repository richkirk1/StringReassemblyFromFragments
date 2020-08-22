import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * StringReassembly.java test cases.
 *
 * @author Rich Kirk
 *
 */
public class StringReassemblyTest {

    /**
     * Construct and return a {@code Set<String>} containing the given
     * {@code String}s.
     *
     * @param args
     *            the {@code String}s to put in the set
     * @return {@code Set<String>} of the given {@code String}s
     * @ensures createFromArgs = [the Set<String> of the given Strings]
     */
    private static Set<String> createFromArgs(String... args) {
        Set<String> set = new Set2<String>();
        for (String s : args) {
            set.add(s);
        }
        return set;
    }

    /**
     * Test combination with a simple overlapping string.
     */
    @Test
    public void testCombination1() {
        String str1 = "12";
        String str2 = "23";
        int overlap = 1;
        String combination = StringReassembly.combination(str1, str2, overlap);
        assertEquals(combination, "123");
    }

    /**
     * Test combination with a more routine string and overlap.
     */
    @Test
    public void testCombination2() {
        String str1 = "template";
        String str2 = "platelets";
        int overlap = Integer.parseInt("5");
        String combination = StringReassembly.combination(str1, str2, overlap);
        assertEquals(combination, "templatelets");
    }

    /**
     * Test combination with a long overlap.
     */
    @Test
    public void testCombination3() {
        String str1 = "ABCDEFGHIJKLMNOPQRSTUVW";
        String str2 = "DEFGHIJKLMNOPQRSTUVWXYZ";
        int overlap = Integer.parseInt("20");
        String combination = StringReassembly.combination(str1, str2, overlap);
        assertEquals(combination, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    /**
     * Test addToSetAvoidingSubstrings by trying to add a string to set that is
     * a substring of one of the set elements.
     */
    @Test
    public void testAddToSetAvoidingSubstrings1() {
        Set<String> strSet = createFromArgs("cat", "fat", "bat");
        Set<String> strSetBefore = createFromArgs("cat", "fat", "bat");
        String str = "ca";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSet, strSetBefore);
    }

    /**
     * Test addToSetAvoidingSubstrings by trying to add a string to a set that
     * is not a substring of one of the set elements where no set elements are
     * substrings of the str.
     */
    @Test
    public void testAddToSetAvoidingSubstrings2() {
        Set<String> strSet = createFromArgs("cat", "fat", "bat");
        Set<String> strSetExpected = createFromArgs("cat", "fat", "bat", "rat");
        String str = "rat";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSet, strSetExpected);
    }

    /**
     * Test addToSetAvoidingSubstrings by trying to add a string to a set that
     * is not a substring of one of the set elements where a one set element is
     * a substring of str.
     */
    @Test
    public void testAddToSetAvoidingSubstrings3() {
        Set<String> strSet = createFromArgs("art", "part", "cart");
        Set<String> strSetExpected = createFromArgs("carton", "part");
        String str = "carton";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSet, strSetExpected);
    }

    /**
     * Test linesFromInput with an easy first file.
     */
    @Test
    public void testLinesFromInputFile1() {
        Set<String> linesFromInputExpected = createFromArgs("a", "b", "c", "d",
                "e");
        SimpleReader inFile = new SimpleReader1L(
                "C:\\Users\\Rich Kirk\\Documents\\OsuCseWsTemplate\\"
                        + "workspace\\StringReassemblyFromFragments\\src\\"
                        + "line_from_input_test1.txt");
        Set<String> linesFromInput = StringReassembly.linesFromInput(inFile);
        assertEquals(linesFromInputExpected, linesFromInput);
    }

    /**
     * Test linesFromInput with a file with more complex text and more lines
     * compared to the previous.
     */
    @Test
    public void testLinesFromInputFile2() {
        Set<String> linesFromInputExpected = createFromArgs("four", "score",
                "and", "seven", "years ago", "our fathers");
        SimpleReader inFile = new SimpleReader1L(
                "C:\\Users\\Rich Kirk\\Documents\\OsuCseWsTemplate\\"
                        + "workspace\\StringReassemblyFromFragments\\src\\"
                        + "line_from_input_test2.txt");
        Set<String> linesFromInput = StringReassembly.linesFromInput(inFile);
        assertEquals(linesFromInputExpected, linesFromInput);
    }
}
