package it.unibo.dna.model;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import it.unibo.dna.model.object.ActivableObjectImpl;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.Puddle;
import it.unibo.dna.model.object.player.State.StateEnum;
import it.unibo.dna.model.object.player.api.Player;
import it.unibo.dna.model.object.api.Entity;

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
                p.getState().setStateX(StateEnum.STATE_STANDING);
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
            if (p.getVector().getX() == 0 && pt.getLastVector().getX() != 0) {
                p.setVectorX(pt.getLastVector().getX());
            } else {
                p.setVectorX(p.getVector().getX() + pt.getVector().getX() - pt.getLastVector().getX());
            }
            if (pt.getVector().getX() != 0 || pt.getLastVector().getX() != 0) {
                pt.setLastVector(pt.getVector());
            }
            if (p.getPosition().getY() + p.getBoundingBox().getHeight() > pt.getPosition().getY() &&
                    p.getPosition().getY() + p.getBoundingBox().getHeight() < pt.getPosition().getY()
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
    public Event hitDoorEvent(final Door door, final Player player, final Score score) {
        return game -> {
            door.openDoor(player, score);
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
    public Event hitDiamondEvent(final Diamond d, final Score s) {
        return game -> {
            game.removeEntity(d);
            s.addScore(d.getValue());
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
                p.getState().setStateX(StateEnum.STATE_STANDING);
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
    public Event soundEvent(String s) {
        return game -> {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File("src\\main\\resources\\sounds\\" + s + ".wav")));
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public Event hitPuddleEvent(Puddle puddle, Player player) {
        return game -> {
            puddle.killPlayer(player);
        };
    }

}
