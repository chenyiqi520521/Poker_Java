// * * * * * * * * * * * * * * * * * * * * * * * *
// * REDROCK-TEAM HOMEWORK 3 (20151017)          *
// * Level 4 - Texas Poker                       *
// * Author:  Haruue Icymoon                     *
// * Website: http://www.caoyue.com.cn/          *
// * * * * * * * * * * * * * * * * * * * * * * * *

package cn.com.caoyue.game.poker;

import java.util.Random;  //提供对随机数的支持

public class Poker {
    private Card[] poker;

    //初始化牌堆。按照 spades-2, clubs-2, hearts-2, diamonds-2, ..., spades-10, ..., spades-K, ..., spades-A, ..., BlackJoker, RedJoker 排列
    public Poker(boolean isNeedJoker) {
        int index = 0;
        poker = new Card[isNeedJoker ? 54 : 52];
        for (Card.Point point : Card.Point.values()) {
            for (Card.Suit suit : Card.Suit.values()) {
                poker[index] = new Card(suit.name(), point.name());
                index++;
            }
        }
        if (isNeedJoker) {
            poker[52] = new Card("Black");
            poker[53] = new Card("Red");
        }
    }

    public Card[] getPoker() {
        return poker;
    }

    //单次交叉洗牌
    public void crossShuffle() {
        Card[] pokerA = new Card[poker.length / 2];
        System.arraycopy(poker, 0, pokerA, 0, pokerA.length);
        Card[] pokerB = new Card[poker.length / 2];
        System.arraycopy(poker, pokerA.length, pokerB, 0, pokerB.length);
        System.out.println(Math.random());
        for (int i = 0; i < pokerA.length + pokerB.length; i++) {
            poker[i] = ((i % 2 == 0) ? pokerA : pokerB)[i / 2];
        }
    }

    //三次交叉洗牌
    public void threeTimesCrossShuffle() {
        crossShuffle();
        crossShuffle();
        crossShuffle();
    }

    //交换两张牌
    private void swap(int indexOfCard1, int indexOfCard2) {
        Card temp;
        temp = poker[indexOfCard1];
        poker[indexOfCard1] = poker[indexOfCard2];
        poker[indexOfCard2] = temp;
    }

    //交换法洗牌
    public void swapShuffle() {
        for (int i = poker.length - 1; i > 0; i--) {
            swap(i, new Random(System.currentTimeMillis()).nextInt(i));
        }
    }
}
