package it.unibo.dna.model.events.impl;

import it.unibo.dna.gameloop.GameEngine;
import it.unibo.dna.model.events.api.Event;
import it.unibo.dna.model.events.api.EventFactory;
import it.unibo.dna.model.game.score.Score;
import it.unibo.dna.model.object.entity.api.Entity;
import it.unibo.dna.model.object.movableentity.impl.MovablePlatform;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.player.impl.State.StateEnum;
import it.unibo.dna.model.object.stillentity.impl.ActivableObjectImpl;
import it.unibo.dna.model.object.stillentity.impl.Diamond;
import it.unibo.dna.model.object.stillentity.impl.Door;

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
            if (p.getStateCopy().getX().equals(StateEnum.STATE_JUMPING)
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
    public Event hitDoorEvent(final Door door, final Player player) {
        return game -> {
            if (door.getPlayer().isEmpty()) {
                door.openDoor(player);
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
            GameEngine.playSound("Diamond_sound");
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitBorderXEvent(final Player p) {
        return game -> {
            p.resetY();
            if (p.getStateCopy().getX().equals(StateEnum.STATE_JUMPING)) {
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
    public Event victoryEvent() {
        return game -> {
            GameEngine.playSound("Victory_sound");
            GameEngine.getGameThread().victoryGame();
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event gameOverEvent() {
        return game -> {
            GameEngine.playSound("GameOver_sound");
            GameEngine.getGameThread().loosingGame();
        };
    }

}
