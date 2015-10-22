// * * * * * * * * * * * * * * * * * * * * * * * *
// * REDROCK-TEAM HOMEWORK 3 (20151017)          *
// * Level 4 - Texas Poker                       *
// * Author:  Haruue Icymoon                     *
// * Website: http://www.caoyue.com.cn/          *
// * * * * * * * * * * * * * * * * * * * * * * * *

package cn.com.caoyue.game.poker;

public class Card implements Comparable {
    enum Suit {
        spades("♠"), clubs("♦"), hearts("♥"), diamonds("♣");
        private final String suit;
        private Object o;

        Suit(String suit) {
            this.suit = suit;
        }

        public String getSuit() {
            return this.suit;
        }

        @Override
        public String toString() {
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

        @Override
        public String toString() {
            return this.point;
        }
    }

    enum Joker {
        Red("RedJoker"), Black("BlackJoker");
        private String name;

        Joker(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private final Suit suit;
    private final Point point;
    private final Joker joker;
    private final boolean isJoker;

    public Card(String suit, String point) {
        this.suit = Suit.valueOf(suit);
        this.point = Point.valueOf(point);
        this.isJoker = false;
        this.joker = Joker.Black;
    }

    public Card(String joker) {
        this.joker = Joker.valueOf(joker);
        this.isJoker = true;
        this.suit = Suit.spades;
        this.point = Point.A;
    }

    public String getSuit() {
        return isJoker ? joker.toString() : suit.toString();
    }

    public String getPoint() {
        return isJoker ? joker.toString() : point.toString();
    }

    public int getPointNum() {
        return isJoker ? (joker == Joker.Black ? 15 : 16) : point.ordinal() + 2;
    }

    public Suit getSuitObj() {
        return suit;
    }

    @Override
    public String toString() {
        return isJoker ? joker.toString() : (suit.toString() + point.toString());
    }

    @Override
    public int compareTo(Object o) {
        if (this == o || getPointNum() == ((Card) o).getPointNum()) {
            return 0;
        }
        if (getPointNum() < ((Card) o).getPointNum()) {
            return -1;
        }
        if (getPointNum() > ((Card) o).getPointNum()) {
            return 1;
        }
        return 0;
    }
}
