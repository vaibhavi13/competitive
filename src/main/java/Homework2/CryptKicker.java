package Homework2;

import java.util.*;

public class CryptKicker {
    private static char[] textDict;
    private static char[] wordDict;

    private static String decrypt(List<String> dict, String input) {
        textDict = new char[26];
        wordDict = new char[26];

        String text[] = input.split(" ");
        boolean found = find(text, dict, 0);

        StringBuilder sb = new StringBuilder();
        if (!found) {
            sb = new StringBuilder();
            for (String t : text) {
                for (int i = 0; i < t.length(); i++) {
                    sb.append("*");
                }
                sb.append(" ");
            }
        } else {
            text = input.split(" ");
            for (String t : text) {
                for (int i = 0; i < t.length(); i++) {
                    sb.append(textDict[t.charAt(i) - 'a']);
                }
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    private static boolean find(String[] text, List<String> dict, int textIndex) {
        if (textIndex == text.length) {
            return true;
        }
        String t = text[textIndex];

        for (int i = 0; i < dict.size(); i++) {
            String word = dict.get(i);
            if (t.length() != word.length()) continue;
            boolean match = true;
            // comparison logic
            HashSet<Character> a_tempChar = new HashSet<>();
            HashSet<Character> w_tempChar = new HashSet<>();

            for (int k = 0; k < t.length(); k++) {

                if ((textDict[t.charAt(k) - 'a'] != 0 && textDict[t.charAt(k) - 'a'] != word.charAt(k)) ||
                        (wordDict[word.charAt(k) - 'a'] != 0 && wordDict[word.charAt(k) - 'a'] != t.charAt(k))) {
                    match = false;
                    for (Character c : a_tempChar) {
                        textDict[c - 'a'] = 0;
                    }
                    for (Character c : w_tempChar) {
                        wordDict[c - 'a'] = 0;
                    }
                    break;
                } else {
                    textDict[t.charAt(k) - 'a'] = word.charAt(k);
                    wordDict[word.charAt(k) - 'a'] = t.charAt(k);
                    a_tempChar.add(t.charAt(k));
                    w_tempChar.add(word.charAt(k));
                }
            }
            if (match) {
                if (find(text, dict, textIndex + 1)) {
                    return true;
                }
                for (Character c : a_tempChar) {
                    textDict[c - 'a'] = 0;
                }
                for (Character c : w_tempChar) {
                    wordDict[c - 'a'] = 0;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<String> dict = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dict.add(sc.next());
        }
        sc.nextLine();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            System.out.println(decrypt(dict, input));
        }

//        List<String> dict = Arrays.asList("and", "dick", "jane", "puff", "spot", "yertle");
//        String input = "bjvg xsb hxsn xsb qymm xsb rqat xsb pnetfn";

//        List<String> dict = Arrays.asList("baseball", "football", "basketball", "tennis", "ball", "is", "a", "sport", "which", "uses", "not", "one", "two", "player", "players", "too", "i", "like", "also", "these", "are", "sports", "soccer", "no", "chess", "btw", "by", "the", "way");
//        String input = "lrsglrww as r sexvh";

 //       System.out.println(decrypt(dict, input));
    }
}
