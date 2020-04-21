import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.lang.*;
 
public class MineSweeper extends JPanel implements MouseListener, ActionListener{

    JFrame f;
    JPanel panel,topPanel,menuPanel,smilePanel;
    JMenuBar menuBar;
    JLabel time,flagCountDown,timeLabel,score;
    JMenu menu,menu2;
    JMenuItem item1,item2,item3,item4,item5,item6;
    JButton resetButton;
    JToggleButton[][]togglers;
    int dimX,dimY;
    ImageIcon mine,one,two,three,four,five,six,seven,eight,flag,beige,mineRed,smiley,dead,cool,oneG,twoG,threeG,fourG,fiveG,sixG,sevenG,eightG,beigeG,oneB,twoB,threeB,fourB,fiveB,sixB,sevenB,eightB,beigeB;
    int r,c;
    int mineTotal;
    int mineMax;
    int mineCount = 0;
    int state = 0;
    int winCount = 0;
    timeThread timer;
    boolean[][] mines;
    int [][] neighbors;
    String themeKey = "Classic";
 
    public MineSweeper(int dimXX, int dimYY, int mineMaxx){

        dimX = dimXX;
        dimY = dimYY;
        mineMax = mineMaxx;
        mineTotal = mineMaxx;
        state = 0;
        mineCount = 0;

        f = new JFrame("MineSweeper");
        f.setSize(40*dimY,40*dimX + 120);
        f.add(this);

        mine = new ImageIcon("images/mine.jpeg");
        mine = new ImageIcon(mine.getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH));
        mineRed = new ImageIcon("images/redMine.jpg");
        mineRed = new ImageIcon(mineRed.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        one = new ImageIcon("images/one.png");
        one = new ImageIcon(one.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        two = new ImageIcon("images/two.png");
        two = new ImageIcon(two.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        three = new ImageIcon("images/three.png");
        three = new ImageIcon(three.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        four = new ImageIcon("images/four.png");
        four = new ImageIcon(four.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        five = new ImageIcon("images/five.png");
        five = new ImageIcon(five.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        six = new ImageIcon("images/six.png");
        six = new ImageIcon(six.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        seven = new ImageIcon("images/seven.png");
        seven = new ImageIcon(seven.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        eight = new ImageIcon("images/eight.png");
        eight = new ImageIcon(eight.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        flag = new ImageIcon("images/flag.png");
        flag = new ImageIcon(flag.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        beige = new ImageIcon("images/beige.png");
        beige = new ImageIcon(beige.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        smiley = new ImageIcon("images/smiley.png");
        smiley = new ImageIcon(smiley.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        dead = new ImageIcon("images/dead.png");
        dead = new ImageIcon(dead.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        cool = new ImageIcon("images/cool.jpg");
        cool = new ImageIcon(cool.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));



        oneG = new ImageIcon("images/oneG.png");
        oneG = new ImageIcon(oneG.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        twoG = new ImageIcon("images/twoG.jpeg");
        twoG = new ImageIcon(twoG.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        threeG = new ImageIcon("images/threeG.jpeg");
        threeG = new ImageIcon(threeG.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        fourG = new ImageIcon("images/fourG.jpeg");
        fourG = new ImageIcon(fourG.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        fiveG = new ImageIcon("images/fiveG.jpeg");
        fiveG = new ImageIcon(fiveG.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        sixG = new ImageIcon("images/sixG.jpeg");
        sixG = new ImageIcon(sixG.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        sevenG = new ImageIcon("images/sevenG.jpg");
        sevenG = new ImageIcon(sevenG.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        eightG = new ImageIcon("images/eightG.jpeg");
        eightG = new ImageIcon(eightG.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        beigeG = new ImageIcon("images/beigeG.jpg");
        beigeG = new ImageIcon(beigeG.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        oneB = new ImageIcon("images/oneB.jpeg");
        oneB = new ImageIcon(oneB.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        twoB = new ImageIcon("images/twoB.jpg");
        twoB = new ImageIcon(twoB.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        threeB = new ImageIcon("images/threeB.jpg");
        threeB = new ImageIcon(threeB.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        fourB = new ImageIcon("images/fourB.jpg");
        fourB = new ImageIcon(fourB.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        fiveB = new ImageIcon("images/fiveB.jpg");
        fiveB = new ImageIcon(fiveB.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        sixB = new ImageIcon("images/sixB.svg");
        sixB = new ImageIcon(sixB.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        sevenB = new ImageIcon("images/sevenB.png");
        sevenB = new ImageIcon(sevenB.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        eightB = new ImageIcon("images/eightB.jpeg");
        eightB = new ImageIcon(eightB.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        beigeB = new ImageIcon("images/beigeB.jpg");
        beigeB = new ImageIcon(beigeB.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));



        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(40*dimY,100));
        smilePanel = new JPanel();
        menuBar = new JMenuBar();
        menu = new JMenu("Game");
        item1 = new JMenuItem("Beginner");
        item1.addActionListener(this);
        item2 = new JMenuItem("Intermediate");
        item2.addActionListener(this);
        item3 = new JMenuItem("Expert");
        item3.addActionListener(this);
        menu2 = new JMenu("Theme");
        item4 = new JMenuItem("Classic");
        item4.addActionListener(this);
        item5 = new JMenuItem("Gold");
        item5.addActionListener(this);
        item6 = new JMenuItem("Dark");
        item6.addActionListener(this);
    
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu2.add(item4);
        menu2.add(item5);
        menu2.add(item6);
        menuBar.add(menu);
        menuBar.add(menu2);
        //menuPanel.add(menuBar);
        topPanel.add(menuBar, BorderLayout.NORTH);

        resetButton = new JButton(smiley);
        resetButton.addActionListener(this);
        time = new JLabel(" Time: ");
        timeLabel = new JLabel("0");
        flagCountDown = new JLabel("Mines Left: ");
        score = new JLabel(Integer.toString(mineTotal) + " mines   ");

        smilePanel.add(flagCountDown);
        smilePanel.add(score);
        smilePanel.add(resetButton);
        smilePanel.add(time);
        smilePanel.add(timeLabel);
        topPanel.add(smilePanel, BorderLayout.SOUTH);


        togglers = new JToggleButton [dimX][dimY];
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(40*dimY,40*dimX));
        panel.setLayout(new GridLayout(dimX, dimY));
        for(int i=0; i<dimX;i++){
            for(int j=0; j<dimY;j++){
                togglers[i][j] = new JToggleButton();
                togglers[i][j].addMouseListener(this);
                panel.add(togglers[i][j]);
            }
        }

        timer = new timeThread(this);

        f.add(topPanel, BorderLayout.NORTH);
        f.add(panel, BorderLayout.SOUTH);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void mouseEntered(MouseEvent e){


    }
    public void mouseExited(MouseEvent e){


    }
    public void mouseClicked(MouseEvent e){


    }
    public void mousePressed(MouseEvent e){


    }
    public boolean allToggled(){
        boolean bool = false;
        for(int x=0;x<dimX;x++){
            for(int y=0;y<dimY;y++){
                if(togglers[x][y].getIcon()!=null){
                    bool = true;
                }
                else{
                    
                    return false;
                }
            }
        }
        return true;
    }
    public boolean gameWon() {
        for(int x=0;x<dimX;x++){
            for(int y=0;y<dimY;y++){
                if(togglers[x][y].getIcon()==flag && mines[x][y] ||togglers[x][y].getIcon()==null && mines[x][y] ){
                    winCount++;
                    
                }
            }
        }
        if(winCount == mineMax){
            
            return true;
            
        }
        
        return false; 
    }
    public void dropMines(int r, int c)
    {
        mines = new boolean[dimX][dimY];
        for (int i = 0; i < dimX; i++){
            for (int j = 0; j < dimY; j++){
                mines[i][j] = false;
            }
        }
        while(mineCount<mineMax){
            int rand = (int)(Math.random() * dimX);
            int rand2 = (int)(Math.random() * dimY);
            if(mines[rand][rand2] == false && !(rand>= r-1 && rand<=r+1 && rand2>=c-1 && rand2<=c+1)){
                mines[rand][rand2] = true;
                
                mineCount ++;
            }
        }
        neighbors = new int[dimX][dimY];
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                if (mines[i][j]) {
                    for (int a = -1; a <= 1 ; a++) {
                        for (int b = -1; b <= 1; b++) {
                            try{
                                if (!mines[i+a][j+b]) {
                                    neighbors[i+a][j+b]++;
                                }
                            }
                            catch (Exception ee) {
                                // Do nothing
                            }
                        }
                    }
                }
            }
        }
    }
    public void mouseReleased(MouseEvent e){
        for(int i=0; i<dimX;i++){
            for(int j=0; j<dimY;j++){
                if(e.getSource() == togglers[i][j]){
                    r = i;
                    c = j;
                }
            }
        }
        if(e.getButton() == MouseEvent.BUTTON1 && state<2){
            if(state==0){
                dropMines(r,c);
                timer = new timeThread(this);
                timer.start();
                state=1;  
            }
            if(mines[r][c]){
                togglers[r][c].setIcon(mine);
                revealMines();
                timer.stop();
                state = 3;
            }
            else{
                expand(r,c);
                if(allToggled()){
                    
                    if(gameWon()){
                        resetButton.setIcon(cool);
                        timer.stop();
                        state = 3;
                        
                    }
                }
            }   
        }
        if(e.getButton() == MouseEvent.BUTTON3 && state<2){
            if(togglers[r][c].getIcon()==null){
                togglers[r][c].setIcon(flag);  
                
                mineTotal--;
                this.score.setText(Integer.toString(mineTotal) + " mines   ");
            }
            else if(togglers[r][c].getIcon()==flag){
                  togglers[r][c].setIcon(null); 
                  
                  mineTotal++;
                  this.score.setText(Integer.toString(mineTotal) + " mines   ");
            } 
            if(allToggled()){
                
                if(gameWon()){
                    resetButton.setIcon(cool);
                    timer.stop();
                    state = 3;
                    
                }
            }
        }    
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == item1){
            dimX=9;
            dimY=9;
            mineMax=10;
            mineTotal =10;
            timer.stop();
            timer = new timeThread(this);
            reset();
        }
        if(e.getSource() == item2){
            dimX=16;
            dimY=16;
            mineMax=40;
            mineTotal = 40;
            timer.stop();
            timer = new timeThread(this);
            reset();
        }
        if(e.getSource() == item3){
            dimX=16;
            dimY=30;
            mineMax=99;
            mineTotal = 99;
            timer.stop();
            timer = new timeThread(this);
            reset();
        }
        if(e.getSource() == item4){ //CLASSIC THEME
            if(mineMax == 10){
                mineTotal=10;
            }
            else if(mineMax == 5){
                mineTotal=5;
            }
            else if(mineMax == 99){
                mineTotal=99;
            }
            themeKey = "Classic";
            timer.stop();
            timer = new timeThread(this);
            reset();
        }
        if(e.getSource() == item5){ //GOLD THEME
            if(mineMax == 10){
                mineTotal=10;
            }
            else if(mineMax == 5){
                mineTotal=5;
            }
            else if(mineMax == 99){
                mineTotal=99;
            }
            themeKey = "Gold";
            timer.stop();
            timer = new timeThread(this);
            reset();
        }
        if(e.getSource() == item6){ //DARK THEME
            if(mineMax == 10){
                mineTotal=10;
            }
            else if(mineMax == 5){
                mineTotal=5;
            }
            else if(mineMax == 99){
                mineTotal=99;
            }
            themeKey = "Dark";
            timer.stop();
            timer = new timeThread(this);
            reset();
        }
        if(e.getSource() == resetButton){
            if(mineMax == 10){
                mineTotal=10;
            }
            else if(mineMax == 5){
                mineTotal=5;
            }
            else if(mineMax == 99){
                mineTotal=99;
            }
            timer.stop();
            timer = new timeThread(this);
            reset();
        }
    }
    public void revealMines(){  
        for(int x=0;x<dimX;x++){
            for(int y=0;y<dimY;y++){
                if(mines[x][y]){
                    togglers[x][y].setIcon(mineRed);
                    resetButton.setIcon(dead);
                }
            }
        }
    }
    public void timer() {
        String[] time = this.timeLabel.getText().split(" ");
        int time0 = Integer.parseInt(time[0]);
        if(state!=0)
            time0++;
        this.timeLabel.setText(Integer.toString(time0) + " s");
    }
    public void reset(){
        f.setSize(40*dimY,40*dimX);
        f.remove(panel);
        f.remove(menuBar);
        f.remove(topPanel);
        f.remove(smilePanel);
        mines = new boolean[dimX][dimY];
        neighbors = new int[dimX][dimY];
        state = 0;
        mineCount = 0;
        winCount = 0;
        togglers = new JToggleButton [dimX][dimY];
        panel = new JPanel();
        panel.setLayout(new GridLayout(dimX, dimY));
        for(int i=0; i<dimX;i++){
            for(int j=0; j<dimY;j++){
                togglers[i][j] = new JToggleButton();
                togglers[i][j].addMouseListener(this);
                panel.add(togglers[i][j]);
            }
        }
        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(40*dimY,100));
        smilePanel = new JPanel();
        menuBar = new JMenuBar();
        menu = new JMenu("Game");
        item1 = new JMenuItem("Beginner");
        item1.addActionListener(this);
        item2 = new JMenuItem("Intermediate");
        item2.addActionListener(this);
        item3 = new JMenuItem("Expert");
        item3.addActionListener(this);
        menu2 = new JMenu("Theme");
        item4 = new JMenuItem("Classic");
        item4.addActionListener(this);
        item5 = new JMenuItem("Gold");
        item5.addActionListener(this);
        item6 = new JMenuItem("Dark");
        item6.addActionListener(this);
    
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu2.add(item4);
        menu2.add(item5);
        menu2.add(item6);
        menuBar.add(menu);
        menuBar.add(menu2);
        topPanel.add(menuBar, BorderLayout.NORTH);

        resetButton = new JButton(smiley);
        resetButton.addActionListener(this);
        time = new JLabel(" Time: ");
        timeLabel = new JLabel("0");
        flagCountDown = new JLabel("Mines Left: ");
        score = new JLabel(Integer.toString(mineTotal) + " mines   ");

        smilePanel.add(flagCountDown);
        smilePanel.add(score);
        smilePanel.add(resetButton);
        smilePanel.add(time);
        smilePanel.add(timeLabel);
        topPanel.add(smilePanel, BorderLayout.SOUTH);


        togglers = new JToggleButton [dimX][dimY];
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(40*dimY,40*dimX));
        panel.setLayout(new GridLayout(dimX, dimY));
        for(int i=0; i<dimX;i++){
            for(int j=0; j<dimY;j++){
                togglers[i][j] = new JToggleButton();
                togglers[i][j].addMouseListener(this);
                panel.add(togglers[i][j]);
            }
        }

        timer = new timeThread(this);
        f.setSize(40*dimY,40*dimX + 120);
        f.add(topPanel, BorderLayout.NORTH);
        f.add(panel, BorderLayout.SOUTH);
        f.revalidate();

    }
    public void expand(int i, int j){
        if(themeKey.equals("Classic")){
            if(togglers[i][j].getIcon()==null){
                if(neighbors[i][j]== 0){
                    togglers[i][j].setIcon(beige);
                    for (int a = i-1; a <= i+1 ; a++) {
                        for (int b = j-1; b <= j+1; b++) {
                            try{
                                if (togglers[a][b].getIcon()==null) {
                                    expand(a,b);
                                }
                            }
                            catch (Exception ee) {
                                // Do nothing
                            }
                        }
                    }
                }
                else if(neighbors[i][j]== 1){
                    togglers[i][j].setIcon(one);
                    
                }
                else if(neighbors[i][j]== 2){
                    togglers[i][j].setIcon(two);
                    
                }
                else if(neighbors[i][j]== 3){
                    togglers[i][j].setIcon(three);
                    
                }
                else if(neighbors[i][j]== 4){
                    togglers[i][j].setIcon(four);
                    
                }
                else if(neighbors[i][j]== 5){
                    togglers[i][j].setIcon(five);
                    
                }
                else if(neighbors[i][j]== 6){
                    togglers[i][j].setIcon(six);
                    
                }
                else if(neighbors[i][j]== 7){
                    togglers[i][j].setIcon(seven);
                    
                }
                else if(neighbors[i][j]== 8){
                    togglers[i][j].setIcon(eight);
                    
                }
            }
        }
        else if(themeKey.equals("Gold")){
            if(togglers[i][j].getIcon()==null){
                if(neighbors[i][j]== 0){
                    togglers[i][j].setIcon(beigeG);
                    for (int a = i-1; a <= i+1 ; a++) {
                        for (int b = j-1; b <= j+1; b++) {
                            try{
                                if (togglers[a][b].getIcon()==null) {
                                    expand(a,b);
                                }
                            }
                            catch (Exception ee) {
                                // Do nothing
                            }
                        }
                    }
                }
                else if(neighbors[i][j]== 1){
                    togglers[i][j].setIcon(oneG);
                    
                }
                else if(neighbors[i][j]== 2){
                    togglers[i][j].setIcon(twoG);
                    
                }
                else if(neighbors[i][j]== 3){
                    togglers[i][j].setIcon(threeG);
                    
                }
                else if(neighbors[i][j]== 4){
                    togglers[i][j].setIcon(fourG);
                    
                }
                else if(neighbors[i][j]== 5){
                    togglers[i][j].setIcon(fiveG);
                    
                }
                else if(neighbors[i][j]== 6){
                    togglers[i][j].setIcon(sixG);
                    
                }
                else if(neighbors[i][j]== 7){
                    togglers[i][j].setIcon(sevenG);
                    
                }
                else if(neighbors[i][j]== 8){
                    togglers[i][j].setIcon(eightG);
                    
                }
            }
        }
        else if(themeKey.equals("Dark")){
            if(togglers[i][j].getIcon()==null){
                if(neighbors[i][j]== 0){
                    togglers[i][j].setIcon(beigeB);
                    for (int a = i-1; a <= i+1 ; a++) {
                        for (int b = j-1; b <= j+1; b++) {
                            try{
                                if (togglers[a][b].getIcon()==null) {
                                    expand(a,b);
                                }
                            }
                            catch (Exception ee) {
                                // Do nothing
                            }
                        }
                    }
                }
                else if(neighbors[i][j]== 1){
                    togglers[i][j].setIcon(oneB);
                    
                }
                else if(neighbors[i][j]== 2){
                    togglers[i][j].setIcon(twoB);
                    
                }
                else if(neighbors[i][j]== 3){
                    togglers[i][j].setIcon(threeB);
                    
                }
                else if(neighbors[i][j]== 4){
                    togglers[i][j].setIcon(fourB);
                    
                }
                else if(neighbors[i][j]== 5){
                    togglers[i][j].setIcon(fiveB);
                    
                }
                else if(neighbors[i][j]== 6){
                    togglers[i][j].setIcon(sixB);
                    
                }
                else if(neighbors[i][j]== 7){
                    togglers[i][j].setIcon(sevenB);
                    
                }
                else if(neighbors[i][j]== 8){
                    togglers[i][j].setIcon(eightB);
                    
                }
            }
        }
    }
    public static void main(String[] args) {
        MineSweeper app = new MineSweeper(9,9,10);
    }
}
class timeThread implements Runnable {
    private Thread t;
    private MineSweeper newGame;
    private boolean run = false;

    timeThread(MineSweeper newGame) {
        this.newGame = newGame;
    }

    public void run() { 
        run = true;
        while (run){
            try {
                Thread.sleep(1000);
                newGame.timer();
            }
            catch (InterruptedException e) {
                System.exit(0);
            }
        }
    }

    public void start() {
        if (t==null) {
            t = new Thread(this);
            t.start();
        }
    }
    public void stop(){
        run = false;
    }
}