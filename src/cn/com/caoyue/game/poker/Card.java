// * * * * * * * * * * * * * * * * * * * * * * * *
// * REDROCK-TEAM HOMEWORK 3 (20151017)          *
// * Level 4 - Texas Poker                       *
// * Author:  Haruue Icymoon                     *
// * Website: http://www.caoyue.com.cn/          *
// * * * * * * * * * * * * * * * * * * * * * * * *

package cn.com.caoyue.game.poker;

public class Card {
    enum Suit {
        spades("spades"), clubs("clubs"), hearts("hearts"), diamonds("diamonds");
        private final String suit;

        Suit(String suit) {
            this.suit = suit;
        }

        public String getSuit() {
            return this.suit;
        }
    }

    enum Point {
        two("2"), three("3"), four("4"), five("5"), six("6"), seven("7"), eight("8"), nine("9"), ten("10"), J("J"), Q("Q"), K("K"), A("A");
        private final String point;

        Point(String point) {
            this.point = point;
        }

        public String getPoint() {
            return this.point;
        }

        public String toString() {
            return this.point;
        }
    }

    private Suit suit;
    private Point point;

    public Card(String suit, String point) {
        this.suit = Suit.valueOf(suit);
        this.point = Point.valueOf(point);
    }

    public String getSuit() {
        return suit.toString();
    }

    public String getPoint() {
        return point.toString();
    }

    public int getPointNum() {
        return point.ordinal() + 2;
    }
}
