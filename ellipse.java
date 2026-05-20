public class ellipse extends Shape{
    private int id;
    private int laxis;
    private int saxis;
    private int type = 4;

    public String getSymbol() {
        return "E";
    }

    protected String makeString() {
        return laxis + "," + saxis;
    }

    protected void unpacking(String[] parts) {
        // parts[0] = id, parts[1] = type 이므로 데이터는 [2],[3]
        this.laxis = java.lang.Integer.parseInt(parts[2]);
        this.saxis = java.lang.Integer.parseInt(parts[3]);
    }

    public ellipse(int id, int laxis, int saxis)
    {
        this.id = id;
        this.laxis = laxis;
        this.saxis = saxis;
    }

    public void draw()
    {
        for(int x = -saxis ; x <= saxis ; x++)
        {
            for(int y = -laxis; y <= laxis ; y++)
            {
                if((x*x)/(saxis*saxis) + (y*y)/(laxis*laxis) <= 1)
                {
                    System.out.print("*");
                }else System.out.print(" ");
            }
            System.out.println();
        }
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
