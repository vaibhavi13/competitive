package Homework4;

/*

(1,a)    (2,b)    (3,c)    (4,d)    (5,e)    (6,f)
(1,b)    (2,c)    (3,d)    (4,e)    (5,f)    (6,a)
(1,c)    (2,d)    (3,e)    (4,f)    (5,a)    (6,b)
(1,d)    (2,e)    (3,f)    (4,a)    (5,b)    (6,c)
(1,e)    (2,f)    (3,a)    (4,b)    (5,c)    (6,d)
(1,f)    (2,a)    (3,b)    (4,c)    (5,d)    (6,e)

 */
public class Demo {

    public static void main(String[] args) {

        int a[] = new int[] {1, 2, 3, 4, 5, 6};
        char b[] = new char[] {'a','b', 'c', 'd', 'e', 'f'};

        int p;
        for(int k = 0 ; k < 6 ; k++ ){
            p = k;
            for(int j = 1 ; j <= 6 ; j++){
                p = (p)%b.length;
                System.out.print("(" + j + "," + b[p] + ")    ");
                p++;
            }
            System.out.println();
        }

        String x = "abcabcabcabcabca";
        System.out.println(x.length());
        int hashCode = x.hashCode();
        float fCode = x.hashCode();
        long lCode = x.hashCode();

        System.out.println(hashCode);
        System.out.println(fCode);
        System.out.println(lCode);


    }
}
