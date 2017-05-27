package com.dxenterprise.cumulus.unit_tests;


import com.dxenterprise.cumulus.controller.SinglePGameController;
import com.dxenterprise.cumulus.model.SinglePGameModel;
import com.dxenterprise.cumulus.model.entities.CloudModel;

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
    public void model_random_clouds(){
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


}
