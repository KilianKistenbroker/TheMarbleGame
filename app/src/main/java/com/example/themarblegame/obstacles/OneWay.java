//package com.example.themarblegame.obstacles;
//
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import com.example.themarblegame.interfaces.GameDesigner;
//import com.example.themarblegame.main.PlayerPhysics;
//import com.example.themarblegame.managers.OneWayManager;
//
//public class OneWay implements GameDesigner {
//    private String direction;
//    private Rect oneWay;
//
//    public OneWay(String direction, int startX, int startY, int endX, int height) {
//        this.oneWay = new Rect(startX, startY, endX, startY + height);
//        this.direction = direction;
//    }
//
//    public String getDirection() {
//        return direction;
//    }
//
//    public Rect getOneWay() {
//        return oneWay;
//    }
//
//    //run collision logic here
//    public static void oneWayCollision(PlayerPhysics playerPhysics) {
//        for (int i = 0; i < OneWayManager.getOneWays().size(); i++) {
//            switch (OneWayManager.getOneWays().get(i).getDirection()) {
//                case "left":
//                    if (Rect.intersects(playerPhysics.getBounds(), OneWayManager.getOneWays().get(i).getOneWay()) && playerPhysics.getBounds().left < OneWayManager.getOneWays().get(i).getOneWay().left) {
//                        if (playerPhysics.getXVelocity() > 0) { //right
//                            playerPhysics.reverse();
//                            playerPhysics.setX(OneWayManager.getOneWays().get(i).getOneWay().left - playerPhysics.getCurrentMarble().getWidth());
//                        }
//                    }
//                    break;
//                case "right":
//                    if (Rect.intersects(playerPhysics.getBounds(), OneWayManager.getOneWays().get(i).getOneWay()) && playerPhysics.getBounds().right > OneWayManager.getOneWays().get(i).getOneWay().right) {
//                        if (playerPhysics.getXVelocity() < 0) { //left
//                            playerPhysics.reverse();
//                            playerPhysics.setX(OneWayManager.getOneWays().get(i).getOneWay().right);
//                        }
//                    }
//                    break;
//                case "up":
//                    if (Rect.intersects(playerPhysics.getBounds(), OneWayManager.getOneWays().get(i).getOneWay()) && playerPhysics.getBounds().top < OneWayManager.getOneWays().get(i).getOneWay().top) {
//                        if (playerPhysics.getYVelocity() > 0) { //falling
//                            playerPhysics.setY(OneWayManager.getOneWays().get(i).getOneWay().top);
//                            playerPhysics.setGrounded(true);
//                            playerPhysics.setNewGround(true);
//                            playerPhysics.setJumpCount(0);
//                            playerPhysics.setParachuteInUse(false);
//                            playerPhysics.ricochetDamage();
//                        } else
//                            playerPhysics.setGrounded(false);
//                    }
//                    break;
//                case "down":
//                    if (Rect.intersects(playerPhysics.getBounds(), OneWayManager.getOneWays().get(i).getOneWay()) && playerPhysics.getBounds().bottom > OneWayManager.getOneWays().get(i).getOneWay().bottom) {
//                        if (playerPhysics.getYVelocity() < 0) { //climbing
//                            playerPhysics.setYVelocity(0);
//                            playerPhysics.setY(OneWayManager.getOneWays().get(i).getOneWay().bottom + playerPhysics.getCurrentMarble().getHeight());
//                        }
//                    }
//            }
//        }
//    }
//
//    @Override
//    public void draw(Canvas canvas) {
//        Paint paint = new Paint();
//        paint.setColor(Color.GRAY);
//        canvas.drawRect(oneWay, paint);
//    }
//
//    @Override
//    public void update() {
//
//    }
//}
