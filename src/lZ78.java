import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.Vector;

public class lZ78 {

    public Vector <String> tags;

    public String FRead(File file) throws Exception {
        FileInputStream read = new FileInputStream(file);
        int i=0;String s="";
        while ((i=read.read())!=-1){
            s=s+(char)i;
        }
        read.close();
        return s;
    }

    public void FWrite(File file) throws Exception {
        FileOutputStream write = new FileOutputStream(file);
        String s="";
        for (int i=0;i<tags.size();i++){
            s += tags.get(i);
        }
        write.write(s.getBytes());
        write.close();
    }

    public void FWrite(File file,String str) throws Exception {
        FileOutputStream write = new FileOutputStream(file);
        byte[] s = str.getBytes();
        write.write(s);
        write.close();
    }

    public void  LZ78compress(String str){
        Vector<String> dic = new Vector<>();
        tags = new Vector<>();

        String check="";
        dic.add("");
        int ptr =0;
        for(int i=0;i<str.length();i++){
          check+=str.charAt(i);
          int index = dic.indexOf(check);

          if(index==-1){
              String temp="<";
              dic.add(check);
              String s = Integer.toString(ptr);
              temp+=(s +","+str.charAt(i)+">");
              tags.add(temp);
              check="";
              ptr=0;
          }else{
              ptr=index;

              if(i==str.length()-1){
                  String temp="<";
                  String s = Integer.toString(ptr);
                  temp+=(s +","+"NULL"+">");
                  tags.add(temp);
              }
              else continue;
          }
        }
        for(int i=0;i<tags.size();i++){
            System.out.println(tags.get(i));
        }

        System.out.println("\n\n     Compare Size\n    --------------");
        System.out.println("Original Size = "+str.length() * 8+" Bits");
        System.out.println("Compressed Size = "+tags.size() * (3+3+8)+" Bits\n");

    }

    public String LZ78decompress(Vector<String> tags){
        Vector<String> dic = new Vector<>();
        String result = "";
        dic.add("");

        for(int i=0; i<tags.size(); i++) {

            String posStr = "";
            String str = "";
            int k = 1;//<11212,>

            String subStr = tags.get(i);//<0,A>

            while (subStr.charAt(k) != ',') {
                posStr = posStr + subStr.charAt(k);
                k++;
            }
            int l = k + 1;
            if(tags.get(i).charAt(l)=='N'){
                int pos = Integer.parseInt(posStr);
                str+=dic.get(pos);
                result+=str;
                if(dic.indexOf(str)==-1) dic.add(str);
            }
            else{
                int pos = Integer.parseInt(posStr);
                str+=dic.get(pos);
                str+=tags.get(i).charAt(l);
                result+=str;
                dic.add(str);
            }

        }
        System.out.println(result);
        return result;
    }

    public static void main(String []args) throws Exception {
        lZ78 lZ78 = new lZ78();
        Scanner input = new Scanner(System.in);
        System.out.println("1- compression");
        System.out.println("2- decompression");
        char option = input.next().charAt(0);
        switch (option){
            case '1':
                File f1 = new File("Compression\\input.txt");
                File f2 = new File("Compression\\compressed.txt");
                String s = lZ78.FRead(f1);
                lZ78.LZ78compress(s);
                lZ78.FWrite(f2);
                break;
            case '2':
                File f3 = new File("DeCompression\\decompressed.txt");
                Vector<String> v = new Vector<>();
                System.out.print("Enter Number of tags : ");
                int n=input.nextInt();
                System.out.println("Enter the tags in this form : <1,A>..");
                //EX:<0,A> <0,B> <1,A> <2,A> <4,A> <4,B> <2,B> <7,B> <8,B> <0,C>
                for(int i=0;i<n;i++){
                    String str;
                    str=input.next();
                    v.add(str);
                }
                String s1 =lZ78.LZ78decompress(v);
                lZ78.FWrite(f3,s1);

                break;
            default:
                System.out.println("invalid input!");
        }
    }
}