package Homework1;

import java.util.Scanner;

public class Check {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean present;
        int ibk = -1 , jbk = -1, iwk = -1 , jwk = -1;
        int d = 0;
        String str;
        while(true){
            d++;
            char c[][] = new char[8][8];
            present = false;
            for(int i = 0 ; i < 8 ; i++){
                str = sc.nextLine();
                for(int j = 0 ; j < str.length() ; j++){
                    c[i][j] = str.charAt(j);
                    if(c[i][j] != '.'){
                        present = true;
                    }
                    if(c[i][j] == 'k'){
                        ibk = i ;
                        jbk = j;
                    }
                    if(c[i][j] == 'K'){
                        iwk = i;
                        jwk = j;
                    }
                }
            }
            if(!present){
                break;
            }
            sc.nextLine();
            System.out.println(findCheck(d, c , ibk, jbk , iwk , jwk));
        }
    }

    private static String findCheck(int d, char[][] c, int ibk, int jbk, int iwk, int jwk) {
        if(isWhiteKingChecked(c, iwk, jwk)){
            return "Game #"+d+": white king is in check.";
        }else if(isBlackKingChecked(c, ibk, jbk)){
            return "Game #"+d+": black king is in check.";
        }else{
            return "Game #"+d+": no king is in check.";
        }
    }

    private static boolean isBlackKingChecked(char[][] c, int ibk, int jbk) {
        boolean isCheck = false;
        if(isAttackedByQueen('Q',c,ibk,jbk) || isAttackedByWhitePawn('P',c,ibk,jbk) || isAttackedByRook('R',c,ibk,jbk)
        || isAttackedByBishop('B',c,ibk,jbk) || isAttackedByKing('K',c,ibk,jbk) || isAttackedByKnight('N',c,ibk,jbk)){
            isCheck = true;
        }
        return isCheck;
    }

    private static boolean isWhiteKingChecked(char[][] c, int iwk, int jwk) {
        boolean isCheck = false;
        if(isAttackedByQueen('q',c,iwk,jwk) || isAttackedByBlackPawn('p',c,iwk,jwk) || isAttackedByRook('r',c,iwk,jwk)
                || isAttackedByBishop('b',c,iwk,jwk) || isAttackedByKing('k',c,iwk,jwk) || isAttackedByKnight('n',c,iwk,jwk)){
            isCheck = true;
        }
        return isCheck;
    }

    private static boolean isAttackedByKnight(char n, char[][] c, int i, int j) {

        if(i-1 >= 0){
            if(j-2 >=0 && c[i-1][j-2] == n) return true;
            if(j+2 < 8 && c[i-1][j+2] == n) return true;
        }
        if(i-2 >= 0){
            if(j-1 >=0 && c[i-2][j-1] == n) return true;
            if(j+1 < 8 && c[i-2][j+1] == n) return true;
        }

        if(i+1 < 8){
            if(j-2 >=0 && c[i+1][j-2] == n) return true;
            if(j+2 < 8 && c[i+1][j+2] == n) return true;
        }
        if(i+2 < 8){
            if(j-1 >=0 && c[i+2][j-1] == n) return true;
            if(j+1 < 8 && c[i+2][j+1] == n) return true;
        }

        return false;
    }

    private static boolean isAttackedByKing(char k, char[][] c, int i, int j) {

        if(i+1 < 8){
            if(j-1 >=0 && (c[i+1][j-1] == k || c[i][j-1] == k)) return true;
            if(j+1 < 8 && (c[i+1][j+1] == k || c[i][j+1] == k )) return true;
            if(c[i+1][j] == k) return true;
        }
        if(i-1 >=0){
            if(j-1 >=0 && c[i-1][j-1] == k) return true;
            if(j+1 < 8 && c[i-1][j+1] == k) return true;
            if(c[i-1][j] == k) return true;
        }
        return false;
    }

    private static boolean isAttackedByBishop(char b, char[][] c, int i, int j) {

        int m = i - 1 , n = j-1;
        while(m >= 0 && n >=0){
            if(c[m][n] == b) return true;
            if(c[m][n] != '.') break;
            m--;
            n--;
        }

        m = i + 1;
        n = j-1;
        while(m < 8 && n >=0){
            if(c[m][n] == b) return true;
            if(c[m][n] != '.') break;
            m++;
            n--;
        }

        m = i - 1;
        n = j + 1;
        while(m >= 0 && n < 8){
            if(c[m][n] == b) return true;
            if(c[m][n] != '.') break;
            m--;
            n++;
        }

        m = i + 1;
        n = j + 1;
        while(m < 8 && n < 8){
            if(c[m][n] == b) return true;
            if(c[m][n] != '.') break;
            m++;
            n++;
        }

        return false;
    }

    private static boolean isAttackedByRook(char r, char[][] c, int i, int j) {

        int m = i - 1 , n = j;
        while(m >= 0){
            if(c[m][n] == r) return true;
            if(c[m][n] != '.') break;
            m--;
        }

        m = i + 1;
        n = j;
        while(m < 8){
            if(c[m][n] == r) return true;
            if(c[m][n] != '.') break;
            m++;
        }

        m = i;
        n = j - 1;
        while(n >= 0){
            if(c[m][n] == r) return true;
            if(c[m][n] != '.') break;
            n--;
        }

        m = i;
        n = j + 1;
        while(n < 8){
            if(c[m][n] == r) return true;
            if(c[m][n] != '.') break;
            n++;
        }
        return false;
    }

    private static boolean isAttackedByWhitePawn(char p, char[][] c, int i, int j) {
        if(i+1 < 8){
            if(j-1 >=0 && c[i+1][j-1] == p) return true;
            if(j+1 < 8 && c[i+1][j+1] == p) return true;
        }
        return false;
    }

    private static boolean isAttackedByBlackPawn(char p, char[][] c, int i, int j) {
        if(i-1 >= 0){
            if(j-1 >=0 && c[i-1][j-1] == p) return true;
            if(j+1 < 8 && c[i-1][j+1] == p) return true;
        }
        return false;
    }

    private static boolean isAttackedByQueen(char q, char[][] c, int i, int j) {
        return isAttackedByRook(q,c,i,j) || isAttackedByBishop(q,c,i,j);
    }

}
