import java.security.SecureRandom;

public class Bird extends Shape{
    SecureRandom rn = new SecureRandom();

    public String getSymbol() { return "B"; }

    private int id;
    private int birdcnt;
    private int[] buf = new int[100];
    private int type = 1;

    protected String makeString() {
        // 새 마릿수 기록하기..
        StringBuilder sb = new StringBuilder();
        sb.append(birdcnt).append(",");

        //buf 배열에 어떤새가 들어있는지 알아야하므로 _로 이어붙여서 저장 ex/ 1_2_3
        for (int i = 0; i < birdcnt; i++) {
            sb.append(buf[i]);
            if (i < birdcnt - 1) sb.append("_");
        }
        return sb.toString();
    }

    protected void unpacking(String[] parts) {
        this.birdcnt = java.lang.Integer.parseInt(parts[2]);

        // 언더바로 묶여있던 배열 데이터를 다시 쪼갬
        String[] bufParts = parts[3].split("_");
        for (int i = 0; i < birdcnt; i++) {
            this.buf[i] = java.lang.Integer.parseInt(bufParts[i]);
        }
    }

    public Bird(int _id, int num)
    {
        this.id = _id;
        this.birdcnt = num;
    }

    public void birdChoice()
    {
        for(int i = 0 ; i < birdcnt ; i++)
        {
            buf[i] = rn.nextInt(3) + 1;
        }
    }

    public void draw()
    {
        String Birds = "";

        for(int i = 0; i <birdcnt; i++)
        {
            switch(buf[i])
            {
                case 1:
                    Birds = Birds + "\n\n" + makebird1();
                    break;
                case 2:
                    Birds = Birds + "\n\n" + makebird2();
                    break;
                case 3:
                    Birds = Birds + "\n\n" + makepenguin3();
                    break;
            }
        }
        System.out.println(Birds);
    }

    private String makebird1()
    {
        String bird1 = """
                 ( ` >
         *      /   )       참새
                ^ ^
        """;
        return bird1;
    }

    private String makebird2()
    {
        String bird2 = """
                   ,___,
        *          (O,O)
        *          /)__)      올빼미
                    " "
        """;
        return bird2;
    }

    private String makepenguin3()
    {
        String bird3 = """
                    _,,..
                    /  ^  ^  >               (물고기) <゜)))彡
                   /   ::    \\
                  /.......... \\
                                              <--- 펭귄
                 |  ....      }\\
                 | ::::       } J
                 し'          }J
                   ゝ        く
                    _,,  ^  ,,_
                     (  `  '  )
                """;
        return bird3;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getType(){
        return this.type;
    }

}

