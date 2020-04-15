package model;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BodyModel {

    public World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera orthographicCamera;
    private InputController inputController;

    // Bodies
    private Body testPlayer;
    private Body ground;

    public BodyModel(InputController inputController) {
        this.inputController = inputController;
        world = new World(new Vector2(0,-10f), true);
        // Contact listener must be used by world
        world.setContactListener(new BodyContactListener(this));

        createGround();
        createTestPlayer();

        // Get factory singleton
        BodyFactory bodyFactory = BodyFactory.getInstance(world);

        // Add arrow
        bodyFactory.makeArrowBody(0,0,1, 2f, 3f);
    }


    public void logicStep(float delta) {

        // TODO: When player object is made this needs to be changed from testPlayer
        if(inputController.left) {
            testPlayer.applyForceToCenter(-10, 0, true);
        } else if (inputController.right){
            testPlayer.applyForceToCenter(10, 0, true);
        }
        // Worlds tep tells world to move forwards in time
        world.step(delta, 3, 3);
    }

    // Test Player
    private void createTestPlayer() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(7, 0);

        // Add the body to the world
        testPlayer = world.createBody(bodyDef);

        // Shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 3);

        // Properties of body - fixturedef er typ data for den 'physical' body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // Create the 'physical' body in the world typ render
        testPlayer.createFixture(shape, 0.0f);

        shape.dispose();
    }

    // Static body
    private void createGround() {
        BodyDef bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.StaticBody;
        bodydef.position.set(0, -8);

        // Add to world
        ground = world.createBody(bodydef);

        // Shape - long and narrow
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 1);

        ground.createFixture(shape, 0.0f);
        shape.dispose();
    }
}
