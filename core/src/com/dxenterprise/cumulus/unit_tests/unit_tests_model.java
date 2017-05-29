package com.dxenterprise.cumulus.unit_tests;


import com.dxenterprise.cumulus.controller.SinglePGameController;
import com.dxenterprise.cumulus.model.SinglePGameModel;
import com.dxenterprise.cumulus.model.entities.BirdModel;
import com.dxenterprise.cumulus.model.entities.CloudModel;
import com.dxenterprise.cumulus.model.entities.EntityModel;

import org.junit.Test;

import java.util.List;

/**
 * Created by Xavier Fontes on 24/05/2017.
 */

public class unit_tests_model {

    @Test
    public void model_starts_game_not_losing(){
          SinglePGameModel.getInstance();
          boolean lost = SinglePGameModel.getInstance().isGame_lost();
          assert(lost == false);
      }

      @Test
    public void model_player_moved(){
            float init_x = SinglePGameModel.getInstance().getPlayer().getX();
            float init_y = SinglePGameModel.getInstance().getPlayer().getY();
            SinglePGameModel.getInstance().update(0.5f);
            float end_x = SinglePGameModel.getInstance().getPlayer().getX();
            float end_y = SinglePGameModel.getInstance().getPlayer().getY();

            assert(Math.sqrt(Math.pow((init_x-end_x),2) + Math.pow((init_y-end_y),2)) != 0);
      }

    @Test
    public void model_add_cloud(){
        SinglePGameModel.getInstance().update(0.5f);
        assert(!SinglePGameModel.getInstance().isGame_lost());
        int num_clouds = SinglePGameModel.getInstance().getClouds().size();
        SinglePGameModel.getInstance().addCloud(10,10,0);
        assert(SinglePGameModel.getInstance().getClouds().size() == (num_clouds+1));

    }

    @Test
    public void model_player_lost(){
        SinglePGameModel.getInstance().update(5f);
        assert(SinglePGameModel.getInstance().isGame_lost());

    }

    @Test
    public void model_add_random_clouds(){
        for(int i = 0; i < 100 ; i++)
            SinglePGameModel.getInstance().addCloud(i*100,i*2,0);

        List<CloudModel> clouds = SinglePGameModel.getInstance().getClouds();

        boolean small = false, medium = false, big = false;
        for(int i = 0; i < clouds.size(); i++){
            if(clouds.get(i).getSize() == CloudModel.CloudSize.SMALL){
                small = true;
            } else if(clouds.get(i).getSize() == CloudModel.CloudSize.MEDIUM){
                medium = true;
            } else if(clouds.get(i).getSize() == CloudModel.CloudSize.BIG){
                big =true;
            }
        }

        assert(small && medium && big);

    }

    @Test(timeout = 1000)
    public void model_start_random_clouds(){
        boolean small = false, medium = false, big = false;
        while(!(small && medium && big)){
            List<CloudModel> clouds = SinglePGameModel.getInstance().getClouds();
            for(CloudModel c : clouds){
                if(c.getSize() == CloudModel.CloudSize.SMALL){
                    small = true;
                } else if(c.getSize() == CloudModel.CloudSize.MEDIUM){
                    medium = true;
                } else if(c.getSize() == CloudModel.CloudSize.BIG){
                    big =true;
                }
            }

            SinglePGameModel.getInstance().clear();
        }
    }

    @Test
    public void model_clearing(){
        SinglePGameModel one = SinglePGameModel.getInstance();
        SinglePGameModel two = SinglePGameModel.getInstance();
        assert(one == two);
        one.clear();
        assert(one == two);
        assert(one == null);
    }

    @Test
    public void model_removing_cloud(){
        List<CloudModel> clouds = SinglePGameModel.getInstance().getClouds();
        int siz = clouds.size();
        if(clouds.size() > 0)
            SinglePGameModel.getInstance().remove(clouds.get(0));

        assert(siz > SinglePGameModel.getInstance().getClouds().size());
    }

    @Test
    public void model_starting_zero_score(){
        SinglePGameModel.getInstance();
        assert (SinglePGameModel.getInstance().getHighscore() == 0);

        SinglePGameModel.getInstance().update(0.5f);
        assert (SinglePGameModel.getInstance().getHighscore() != 0);

    }

    @Test
    public void model_forcing_to_lose(){
        SinglePGameModel.getInstance();

        float x = SinglePGameModel.getInstance().getPlayer().getX();

        SinglePGameModel.getInstance().updateLostCondition(x,x-10);
        assert(SinglePGameModel.getInstance().isGame_lost()==false);

        SinglePGameModel.getInstance().updateLostCondition(x,x+10);
        assert (SinglePGameModel.getInstance().isGame_lost());
    }

    @Test
    public void model_test_EntityModel(){
        EntityModel player = SinglePGameModel.getInstance().getPlayer();
        ((EntityModel)player).getRotation();
        ((EntityModel)player).isFlaggedToBeRemoved();
    }

    @Test
    public void model_test_BirdModel(){
        EntityModel b = SinglePGameModel.getInstance().getPlayer();
        float vx = b.getVx();
        float vy = b.getVy();
        float x = b.getX();
        float y = b.getY();
        assert(b.isFlaggedToBeRemoved()==false);
        assert(b.getType() == EntityModel.ModelType.PLAYER);
        assert(b.getRotation() == 0);
        b.setRotation(0);

        assert(b instanceof BirdModel);
        if(!((BirdModel)b).isWalking())
            ((BirdModel)b).setWalking(true);
        b.setPosition(x+10,y-40); //forcing falling out

        b.setVx(vx+1);
        b.setVy(vy-4);
        SinglePGameModel.getInstance().update(0.5f);
        assert(SinglePGameModel.getInstance().isGame_lost());

        BirdModel bird = SinglePGameModel.getInstance().getPlayer();
        bird.getType();
    }

    @Test
    public void model_test_CloudModel(){
        for(int i = 0; i < 100 ; i++)
            SinglePGameModel.getInstance().addCloud(i*100,i*2,0);
        List<CloudModel> clouds = SinglePGameModel.getInstance().getClouds();

        boolean small = false, medium = false, big = false;

        for(CloudModel c : clouds){
            CloudModel.ModelType mod = c.getType();
            if(mod == CloudModel.ModelType.SMALLCLOUD)
                small = true;
            else if (mod == CloudModel.ModelType.MEDIUMCLOUD)
                medium = true;
            else if (mod == CloudModel.ModelType.BIGCLOUD)
                big = true;
        }

        assert(small && medium && big);

        CloudModel c = new CloudModel(0,0,0,null);
        CloudModel.ModelType mod = c.getType();
        assert(mod == null);


    }


    @Test
    public void model_test_highscore(){
        SinglePGameModel.getInstance().getHighscore();
    }

    @Test
    public void model_test_losing(){
        assert(SinglePGameModel.getInstance().getHighscore() == 0);
        SinglePGameModel.getInstance().setGame_lost(true);
        SinglePGameModel.getInstance().update(0.5f);
        assert(SinglePGameModel.getInstance().getHighscore() == 0);
    }

    @Test
    public void model_test_double_jump(){
        boolean jump = SinglePGameModel.getInstance().canJump();
        assert(jump);
        SinglePGameModel.getInstance().birdJump();
        assert(jump);
        SinglePGameModel.getInstance().birdJump();
        assert(!jump);
        SinglePGameModel.getInstance().resetJumps();
        assert(jump);
    }
}
