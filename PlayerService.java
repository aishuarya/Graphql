package com.example.PropertyManager.Service;

import com.example.PropertyManager.Model.Player;
import com.example.PropertyManager.Model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players=new ArrayList<>();
    AtomicInteger id=new AtomicInteger(0);

    public List<Player> findAll(){
        return players;
    }

    public Optional<Player> findOne(Integer id){

        return players.stream().
                filter(player -> player.id() == id).
                findFirst();
    }

    public Player createPlayer(String name, Team team){
        Player player=new Player(id.incrementAndGet(),name,team);
        players.add(player);
        return player;
    }

    public Player deletePlayer(Integer id){

        Player player=players.stream().filter(player1 -> player1.id()==id).findFirst().orElseThrow(()->new IllegalArgumentException());
        players.remove(player);
        players.remove(player);
        return player;
    }
    public Player updatePlayer(Integer id,String name,Team team){

        Player updatedPlayer=new Player(id,name,team);

        Optional<Player> player=players.stream().filter(player1 -> player1.id()==id).findFirst();

        if (player.isPresent()){
            Player player1=player.get();
            int index=players.indexOf(player1);
            players.set(index,updatedPlayer);
        }
        else{
            throw  new IllegalArgumentException("Inavlid");
        }

        return updatedPlayer;
    }

   @PostConstruct
   public  void init(){
        players.add(new Player(id.incrementAndGet(),"MSD",Team.CSK));
       players.add(new Player(id.incrementAndGet(),"VK",Team.RCB));
       players.add(new Player(id.incrementAndGet(),"JAD",Team.CSK));
    }
}
