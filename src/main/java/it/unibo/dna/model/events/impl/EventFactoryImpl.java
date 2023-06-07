package it.unibo.dna.model.events.impl;

import java.util.List;

import it.unibo.dna.GameEngine;
import it.unibo.dna.model.Score;
import it.unibo.dna.model.events.api.Event;
import it.unibo.dna.model.events.api.EventFactory;
import it.unibo.dna.model.object.player.State.StateEnum;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.stillEntity.impl.ActivableObjectImpl;
import it.unibo.dna.model.object.stillEntity.impl.Diamond;
import it.unibo.dna.model.object.stillEntity.impl.Door;
import it.unibo.dna.model.object.stillEntity.impl.Puddle;
import it.unibo.dna.model.object.api.Entity;
import it.unibo.dna.model.object.movableEntity.MovablePlatform;

/**
 * Class that implements the {@link EventFactory} interface.
 */
public class EventFactoryImpl implements EventFactory {
   

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitPlatformEvent(final Entity pt, final Player p) {
        return game -> {
            if (p.getBoundingBox().sideCollision(pt.getPosition(), pt.getBoundingBox().getHeight(),
                    pt.getBoundingBox().getWidth())) {
                p.resetX();
            } else {
                p.resetY();
            }
            if (p.getState().getX().equals(StateEnum.STATE_JUMPING)
                    && p.getPosition().getY() < pt.getPosition().getY()) {
                p.setStateX(StateEnum.STATE_STANDING);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitMovablePlatformEvent(final MovablePlatform pt, final Player p) {
        return game -> {
            p.setVectorY(p.getVector().getY() + pt.getVector().getY());
            if (p.getVector().getX() == 0 && pt.getPreviousVector().getX() != 0) {
                p.setVectorX(pt.getPreviousVector().getX());
            } else {
                p.setVectorX(p.getVector().getX() + pt.getVector().getX() - pt.getPreviousVector().getX());
            }
            if (pt.getVector().getX() != 0 || pt.getPreviousVector().getX() != 0) {
                pt.setPreviousVector(pt.getVector());
            }
            if (p.getPosition().getY() + p.getBoundingBox().getHeight() > pt.getPosition().getY()
                    && p.getPosition().getY() + p.getBoundingBox().getHeight() < pt.getPosition().getY()
                            + pt.getBoundingBox().getHeight()) {
                p.setPositionY(pt.getPosition().getY() - p.getBoundingBox().getHeight());
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitButtonEvent(final ActivableObjectImpl object, final Player player) {
        return game -> {
            if (object.getPlayer().isEmpty()) {
                object.setPlayer(player);
                if (!object.isActivated()) {
                    object.activate();
                } else {
                    object.deactivate();
                    object.resetPlayer();
                }
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitDoorEvent(final Door door, final Player player, final List<Entity> entities) {
        return game -> {
            if (door.getPlayer().isEmpty()) {
                door.openDoor(player);
            }
            final double numberOfOpenedDoors = entities.stream()
                    .filter(entity -> entity instanceof Door)
                    .map(entity -> (Door) entity)
                    .filter(entity -> entity.getDoorState().equals(Door.DoorState.OPEN_DOOR))
                    .count();
            if (numberOfOpenedDoors == 2) {
                game.getEventQueue().addEvent(this.victoryEvent());
                System.out.println("EventFactoryImpl.hitDoorEvent()");
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitLeverEvent(final ActivableObjectImpl object, final Player player) {
        return game -> {
            if (object.getPlayer().isEmpty()) {
                object.setPlayer(player);
                if (object.isActivated()) {
                    object.deactivate();
                } else {
                    object.activate();
                }
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitDiamondEvent(final Diamond d) {
        return game -> {
            game.removeEntity(d);
            Score.addScore(d.getValue());
            GameEngine.playSound("diamond");
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitBorderXEvent(final Player p) {
        return game -> {
            p.resetY();
            if (p.getState().getX().equals(StateEnum.STATE_JUMPING)) {
                p.setStateX(StateEnum.STATE_STANDING);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitBorderYEvent(final Player p) {
        return game -> {
            p.resetX();
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitPuddleEvent(final Puddle puddle, final Player player) {
        return game -> {
            if(puddle.killPlayer(player)) {
                game.getEventQueue().addEvent(this.gameOverEvent());
            }
        };
    }

    @Override
    public Event victoryEvent() {
        return game -> {
            GameEngine.playSound("victory");
            GameEngine.getGameThread().victoryGame();
        };
        
    }

    @Override
    public Event gameOverEvent() {
        return game -> {
            GameEngine.playSound("gameOver");
            GameEngine.getGameThread().loosingGame();
        };
    }

}
