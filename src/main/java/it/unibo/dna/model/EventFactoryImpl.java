package it.unibo.dna.model;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import it.unibo.dna.model.object.ActivableObject;
import it.unibo.dna.model.object.Diamond;
import it.unibo.dna.model.object.Door;
import it.unibo.dna.model.object.MovablePlatform;
import it.unibo.dna.model.object.api.Player;
import it.unibo.dna.model.object.api.Player.State;
import it.unibo.dna.model.object.api.Entity;

/**
 * Class that implements the {@link EventFactory} interface.
 */
public class EventFactoryImpl implements EventFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitPlatformEvent(Entity pt, Player p) {
        return game -> {
            if (p.getBoundingBox().sideCollision(pt.getPosition(), pt.getBoundingBox().getHeight(),
                    pt.getBoundingBox().getWidth())) {
                p.resetX();
            } else {
                p.resetY();
            }
            if (p.getPosition().y < pt.getPosition().y) {
                p.getState().setX(State.STATE_STANDING);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitMovablePlatformEvent(MovablePlatform pt, Player p) {
        return game -> {
            p.setVectorY(p.getVector().y + pt.getVector().y);
            if (p.getVector().x == 0 && pt.getLastVector().x != 0) {
                p.setVectorX(pt.getLastVector().x);
            }
            p.setVectorX(p.getVector().x + pt.getVector().x - pt.getLastVector().x);
            if (pt.getVector().x != 0 || pt.getLastVector().x != 0) {
                pt.setLastVector(pt.getVector());
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitButtonEvent(ActivableObject o, Player p) {
        return game -> {
            if (o.getPlayer().isEmpty()) {
                o.setPlayer(p);
                if (!o.isActivated()) {
                    o.activate();
                } else {
                    o.deactivate();
                    o.resetPlayer();
                }
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitDoorEvent(Door d, Player p) {
        return game -> {
            d.openDoor(p);
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitLeverEvent(ActivableObject o, Player p) {
        return game -> {
            if (o.getPlayer().isEmpty()) {
                o.setPlayer(p);
                if (o.isActivated()) {
                    o.deactivate();
                } else {
                    o.activate();
                }
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitDiamondEvent(Diamond d, Score s) {
        return game -> {
            game.removeEntity(d);
            s.addScore(d.getValue());
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitBorderXEvent(Player p) {
        return game -> {
            p.resetY();
            p.getState().setX(State.STATE_STANDING);
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitBorderYEvent(Player p) {
        return game -> {
            p.resetX();
        };
    }

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

}
