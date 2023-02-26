package Qno3;

public class Q3B {
    public static boolean Matching(String str, String pat) {
        int sIn = 0, pIn = 0;
        int strLen = str.length(), patLen = pat.length();
        while (sIn < strLen && pIn < patLen) {
            if (pat.charAt(pIn) == '@') {
                return true;
            } else if (pat.charAt(pIn) == '#') {
                pIn++;
                sIn++;
            } else if (str.charAt(sIn) == pat.charAt(pIn)) {
                sIn++;
                pIn++;
            } else {
                return false;
            }
        }
        return sIn == strLen && pIn == patLen;
    }

    public static void main(String[] args) {
        String a1 = "tt", pat1 = "@";
        String a2 = "ta", pat2 = "t";
        String a3 = "ta", pat3 = "t#";
        System.out.println(Matching(a1, pat1));
        System.out.println(Matching(a2, pat2));
        System.out.println(Matching(a3, pat3));
    }
}
