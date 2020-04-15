package model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class BodyContactListener implements ContactListener {

    private BodyModel parent;

    public BodyContactListener(BodyModel parent){
        this.parent = parent;
    }

    @Override
    public void beginContact(Contact contact) {
        // Add message to be printed when contact
        System.out.println("Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType() + " has hit " + fb.getBody().getType());

        if(fa.getBody().getType() == BodyDef.BodyType.StaticBody){
            this.repel(fa, fb);
        }
        else if(fb.getBody().getType() == BodyDef.BodyType.StaticBody) {
            this.repel(fb, fa);
        }
        else {
            // Neither a nor b are static objects
        }
    }

    private void repel(Fixture staticFixture, Fixture otherFixture) {
        System.out.println("Adding force");
        otherFixture.getBody().applyForceToCenter(new Vector2(-10000,-10000), true);
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
