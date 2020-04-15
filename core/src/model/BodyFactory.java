package model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class BodyFactory {

    private static BodyFactory thisInstance;
    private World world;

    public static final int ARROW = 0;
    public static final int WOOD = 1;
    public static final int RUBBER = 2;

    // Singleton
    private BodyFactory(World world) {
        this.world = world;
    }

    public static BodyFactory getInstance(World world) {
        if(thisInstance == null) {
            thisInstance = new BodyFactory(world);
        }
        return thisInstance;
    }

    // Making fixture
    static public FixtureDef makeFixture(int material, Shape shape) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        switch (material) {
            case 0:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0.3f;
                fixtureDef.restitution = 0.1f;
                break;
            case 1:
                fixtureDef.density = 0.5f;
                fixtureDef.friction = 0.7f;
                fixtureDef.restitution = 0.3f;
                break;
            case 2:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0f;
                fixtureDef.restitution = 1f;
                break;
            default:
                fixtureDef.density = 7f;
                fixtureDef.friction = 0.5f;
                fixtureDef.restitution = 0.3f;
        }
        return fixtureDef;
    }

    public void makeAllFixturesSensors(Body bod) {
        for(Fixture fix :bod.getFixtureList()){
            fix.setSensor(true);
        }
    }

    // Generate arrow body
    public Body makeArrowBody(float possx, float possy, int material, float velox, float veloy) {
        // Definition params
        BodyDef arrowBodyDef = new BodyDef();
        arrowBodyDef.position.x = possx;
        arrowBodyDef.position.y = possy;
        arrowBodyDef.linearVelocity.set(velox, veloy);
        // Definition sticky for arrows
        arrowBodyDef.type = BodyDef.BodyType.DynamicBody;
        arrowBodyDef.fixedRotation = false;

        // Create body to attach said definitions
        Body arrowBody = world.createBody(arrowBodyDef);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(3,1);
        arrowBody.createFixture(makeFixture(material, polygonShape));

        polygonShape.dispose();
        return arrowBody;
    }


}
