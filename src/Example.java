// * * * * * * * * * * * * * * * * * * * * * * * *
// * REDROCK-TEAM HOMEWORK 3 (20151017)          *
// * Level 3 - Poker & Shuffle                   *
// * Level 4 - Texas Poker                       *
// * Author:  Haruue Icymoon                     *
// * Time:    Fri Oct 23 18:24:31 CST 2015       *
// * Website: http://www.caoyue.com.cn/          *
// * * * * * * * * * * * * * * * * * * * * * * * *

import cn.com.caoyue.game.poker.*;

public class Example {
    public static void main(String[] args) {
        //Card
        Card card1 = new Card("Red");
        Card card2 = new Card("hearts", "ten");
        System.out.println(card1.toString());
        System.out.println(card1.compareTo(card2));
        System.out.println("=====================================");
        //Poker
        Poker poker1 = new Poker(true);
        for (Card i : poker1.getPoker()) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("-------------------------------------");
        poker1.randomMethodShuffle();
        for (Card i : poker1.getPoker()) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("=====================================");
        //TexasPoker
        TexasPoker texasPoker1 = new TexasPoker();
        TexasPoker texasPoker2 = new TexasPoker();
        for (Card i : texasPoker1.getCards()) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (Card i : texasPoker2.getCards()) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(texasPoker1.compareTo(texasPoker2));
    }
}
