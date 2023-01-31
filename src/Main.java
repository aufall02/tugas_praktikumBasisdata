

import java.sql.*;
import java.util.Scanner;


public class Main {
    static  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static  final String DB_URL = "jdbc:mysql://localhost/mahasiswa";
    static  final String USER = "root";
    static  final String PASS = "";

    static  Connection conn;
    static  Statement stmt;
    static  ResultSet rs;



    static Scanner masukan = new Scanner(System.in);

    //update
    public static String update(){
            System.out.println("persiapan merubah data");
            System.out.printf("masukkan id yang mau diubah: ");
            String idp=masukan.next();
            System.out.printf("masukkan nama yang mau diubah: ");
            String namap=masukan.next();
            String sql = "update tbl_mahasiswa set nama='" +namap+"' where id='" +idp+"'" ;

            return sql;
    }
// menghapus data berdasarkan id
    public static String delete(){

        System.out.printf("masukkan id yang mau didelete: ");
        String idp=masukan.next();
        String sql = "delete from tbl_mahasiswa where id='" +idp+"'" ;

        return sql;
    }

    //menapilkan data
    public static void datas(){
       try {

               String sql = "select * from tbl_mahasiswa";
               rs = stmt.executeQuery(sql);
            while (rs.next()){
                System.out.println("ID Mahasiswa: " + rs.getInt("id")
                        + "| NIM: " + rs.getString("nim")
                        + "| Nama Mahasiswa: " + rs.getString("nama")
                        + "| Alamat: " + rs.getString("alamat"));

           }
           return ;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

    }
// menambahkan data
    public static String insert(){
            System.out.println("persiapan input");
            System.out.print("masukkan id: ");

            int idI=masukan.nextInt();
            System.out.print("masukkan nim:");
            String nimI=masukan.next();
            System.out.print("masukkan nama:");
            String namaI=masukan.next();
            System.out.print("masukkan alamat:");
            String alamatI=masukan.next();
            String sql = "insert into tbl_mahasiswa(id,nim,nama,alamat)" + "values('"+idI+"','"+nimI+"','"+namaI+"','"+alamatI+"')";
           return sql;
    }

    public static int menu(){
        System.out.println("=================pilih menuuu ================");
        System.out.println("menu 1: lihat data");
        System.out.println("menu 2: insert data");
        System.out.println("menu 3: update data");
        System.out.println("menu 4: delete data");
        System.out.println("menu 5: exit");
        System.out.print("pilih menu: ");
        int menu=masukan.nextInt();

        return menu;
    }


    public static void main(String[] args) {
//        insert();
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();

            while(true){
                System.out.println("=================pilih menuuu ================");
                System.out.println("menu 1: lihat data");
                System.out.println("menu 2: insert data");
                System.out.println("menu 3: update data");
                System.out.println("menu 4: delete data");
                System.out.println("menu 5: exit");
                System.out.print("pilih menu: ");
                int menu=masukan.nextInt();

                switch (menu){
                    case 1:
                        datas();
                        break;
                    case 2:
                        stmt.execute(insert());
//                        System.out.print("data berhasil di masukkan");
                        break;
                    case 3:
                        stmt.execute(update());
                        System.out.println("data berhasil di update");
                        break;
                    case 4:
                        stmt.execute(delete());
                        System.out.println("data berhasil di delete");
                        break;

                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("menu yang anda masukkan salah, silahkan kembali masukkan menu");
                }
            }


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}