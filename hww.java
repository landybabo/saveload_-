import java.io.*;
import java.util.Scanner;

public class hww {
    public static final int QUIT = 0;

    private static final String FILE_NAME = "shape_data.txt"; //원래 이렇게 하면 문제가 생기는데..

    public static void printMenu()
    {
        System.out.println();
        System.out.println("*********************");
        System.out.println(" 1. Rectangle ");
        System.out.println(" 2. Triangle ");
        System.out.println(" 3. Circle(아마도..?) ");
        System.out.println(" 4. ellipse ");
        System.out.println(" 5. ---- ");
        System.out.println(" 6. 새 ");
        System.out.println(" 7. 지정된 모양중 저장된거 다 그려서 보여주기 ");
        System.out.println(" 8. 모든 그림 다 그려서 보여주기 ");
        System.out.println(" 9. 전부 삭제 ");
        System.out.println(" 10. 저장하기 ");
        System.out.println(" 11. 불러오기 ");
        System.out.println(" 0. QUIT ");
        System.out.println("**********************");
        System.out.print(" Enter Your Choice : ");
    }

    public static Rectangle createRect()
    {
        Rectangle rect;
        int id, length, height;
        char pattern;

        Bird brd;

        Scanner input = new Scanner(System.in);

        System.out.print("Enter ID : ");
        id = input.nextInt();
        System.out.print("Enter Length : ");
        length = input.nextInt();
        System.out.print("Enter height : ");
        height = input.nextInt();
        System.out.print("Enter pattern ");
        pattern = input.next().charAt(0);

        rect = new Rectangle(id, length, height, pattern);
        return rect;
    }

    public static Bird creatBird()
    {
        Bird brd;
        int id, cnt;

        Scanner input = new Scanner(System.in);
        System.out.print("Enter Id : ");
        id = input.nextInt();
        System.out.print("새 몇 마리? : ");
        cnt = input.nextInt();

        brd = new Bird(id,cnt);
        brd.birdChoice();
        return brd;
    }

    public static Triangle creatTriangle()
    {
        Triangle tri;
        int id, width;
        Scanner input = new Scanner(System.in);

        System.out.print("Enter ID : ");
        id = input.nextInt();
        System.out.print("width : ");
        width = input.nextInt();

        tri = new Triangle(id,width);
        return tri;
    }

    public static Circle creatCircle()
    {
        Circle cir;
        int id, r;
        Scanner input = new Scanner(System.in);

        System.out.print("Enter ID : ");
        id = input.nextInt();
        System.out.print("Radius : ");
        r = input.nextInt();

        cir = new Circle(id,r);
        return cir;
    }

    public static ellipse creatEllipse()
    {
        ellipse ell;
        int id,s,l;
        Scanner input = new Scanner(System.in);

        System.out.print("Enter ID : ");
        id = input.nextInt();
        System.out.print("Long Axis : ");
        l = input.nextInt();
        System.out.print("Short Axis : ");
        s = input.nextInt();

        ell = new ellipse(id,l,s);
        return ell;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int select;

        Shape pointer = null;
        Shape[] diagrams = new Shape[1000];
        int diagCnt = 0;

        printMenu();
        select = input.nextInt();
        while (select != QUIT) {

            if (select < 7) {
                switch (select) {
                    case 1:
                        pointer = createRect();
                        break;
                    case 2:
                        pointer = creatTriangle();
                        break;
                    case 3:
                        pointer = creatCircle();
                        break;
                    case 4:
                        pointer = creatEllipse();
                        break;
                    case 5:
                        System.out.println("...");
                        break;
                    case 6:
                        pointer = creatBird();
                        break;
                }

                if (select != 5 && pointer != null) {
                    pointer.draw();
                    diagrams[diagCnt] = pointer;
                    diagCnt++;
                }
            }

            switch (select) {
                case 7:
                    System.out.println("모양");
                    System.out.println(" 0 : Rectangle ");
                    System.out.println(" 1 : 새 ");
                    System.out.println(" 2 : Triangle ");
                    System.out.println(" 3 : Circle ");
                    System.out.println(" 4 : Ellipse ");
                    System.out.print(" 입력 : ");
                    select = input.nextInt();

                    for (int i = 0; i < diagCnt; i++) {
                        if (diagrams[i].getType() == select) diagrams[i].draw();
                    }
                    break;

                case 8:
                    for (int i = 0; i < diagCnt; i++) diagrams[i].draw();
                    break;
                case 9:
                    diagCnt = 0;
                    System.out.println("삭제완료.");
                    break;
                case 10: // 추가, 저장
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                        for (int i = 0; i < diagCnt; i++) {
                            // 각 객체가 알아서 만든 문자열을 파일에 한 줄씩 씀
                            writer.write(diagrams[i].makeit());
                            writer.newLine();
                        }
                        System.out.println(" 파일 저장을 완료 (" + FILE_NAME + ")");
                    } catch (IOException e) {
                        System.out.println(" 오류 발생 ");
                    }
                    break;
                case 11: // 📂 [LOAD] 파일 불러오기 기능
                    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                        String line;
                        diagCnt = 0; // 불러오기 전 초기화
                                    //끝까지 다 읽기
                        while ((line = reader.readLine()) != null) {
                            if (line.trim().isEmpty()) continue;

                            // 심볼이랑 데이터 따로 처리해서 클래스 구분하고 데이터 가르기
                            String[] entry = line.split(":", 2);
                            String symbol = entry[0];
                            String data = entry[1];
                            //불러오는거 저장할곳
                            Shape loadedShape = null;

                            // 구분자를 보고 알맞은 객체를 생성
                            switch (symbol) {
                                case "R": loadedShape = new Rectangle(0, 0, 0, ' '); break;
                                case "T": loadedShape = new Triangle(0, 0); break;
                                case "C": loadedShape = new Circle(0, 0); break;
                                case "E": loadedShape = new ellipse(0, 0, 0); break;
                                case "B": loadedShape = new Bird(0, 0); break;
                            }

                            if (loadedShape != null) {
                                // 껍데기 객체에게 데이터를 던져주면, 알아서 변수들을 채움 (파싱 위임)
                                loadedShape.deserialize(data);
                                // 값이 꽉 찬 객체를 배열에 쏙 넣음
                                diagrams[diagCnt] = loadedShape;
                                diagCnt++;
                            }
                        }
                        System.out.println("성공. (총 " + diagCnt + "개)");
                    } catch (FileNotFoundException e) { //제미나이한테 물어봤어요.. 파일 없을때 이렇게 하라고..
                        System.out.println(" 세이브 파일이 존재하지 않습니다.");
                    } catch (IOException e) {
                        System.out.println(" 오류 ");
                    }
                    break;
            }

        printMenu();
        select = input.nextInt();
        }

        System.out.println("Bye Bye ~~~");
    }
}