package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    Hashtable <String, SoccerPlayer> soccerHash = new Hashtable<String, SoccerPlayer>();
    Set<String> keys = soccerHash.keySet();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {

        if(soccerHash.containsKey(firstName+"##"+lastName)) {
        return false;
    }   else{
            SoccerPlayer soccerPlayer = new SoccerPlayer(firstName,lastName,uniformNumber,teamName);
            soccerHash.put(firstName + "##" + lastName, soccerPlayer);
            return true;


        }



	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {

        if(soccerHash.containsKey(firstName+"##"+lastName)){
            soccerHash.remove(firstName+"##"+lastName);
            return true;
        }else{

        return false;}
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {

        if(soccerHash.containsKey(firstName+"##"+lastName)){

            return soccerHash.get(firstName+"##"+lastName);

        }else{return null;}


    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        if(soccerHash.containsKey(firstName+"##"+lastName)) {
            soccerHash.get(firstName+"##"+lastName).bumpGoals();
            return true;
        }

        return false;
    }


    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        if(soccerHash.containsKey(firstName+"##"+lastName)) {
            soccerHash.get(firstName+"##"+lastName).bumpAssists();
            return true;
        }

        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        if(soccerHash.containsKey(firstName+"##"+lastName)) {
            soccerHash.get(firstName+"##"+lastName).bumpShots();
            return true;
        }

        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        if(soccerHash.containsKey(firstName+"##"+lastName)) {
            soccerHash.get(firstName+"##"+lastName).bumpSaves();
            return true;
        }

        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        if(soccerHash.containsKey(firstName+"##"+lastName)) {
            soccerHash.get(firstName+"##"+lastName).bumpFouls();
            return true;
        }

        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        if(soccerHash.containsKey(firstName+"##"+lastName)) {
            soccerHash.get(firstName+"##"+lastName).bumpYellowCards();
            return true;
        }

        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        if(soccerHash.containsKey(firstName+"##"+lastName)) {
            soccerHash.get(firstName+"##"+lastName).bumpRedCards();
            return true;
        }

        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {

        if(teamName==null){

            return soccerHash.size();

        }else{
            int countOfPlayer =0;
            for(String key: keys ){
                if(teamName.equals( soccerHash.get(key).getTeamName())){

                    countOfPlayer++;

                }

            }

            return countOfPlayer;
        }




	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        int count = 0;
        for (String key: keys) {
            if (teamName == null) {

                if (count == idx) {
                    return soccerHash.get(key);
                }
                count++;

        }else if(teamName.equals( soccerHash.get(key).getTeamName())) {
            if (count == idx) {
                return soccerHash.get(key);
            }
            count++;

            }

        //return null;
        }
    return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        return file.exists();
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {

        PrintWriter p = null;
        try {
            p = new PrintWriter(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (String key: keys) {
            p.println(logString(soccerHash.get(key).getFirstName()));
            p.println(logString(soccerHash.get(key).getLastName()));
            p.println(logString(soccerHash.get(key).getTeamName()));
            p.println(logString("" +soccerHash.get(key).getUniform()));
            p.println(logString("" +soccerHash.get(key).getGoals()));
            p.println(logString("" +soccerHash.get(key).getAssists()));
            p.println(logString("" +soccerHash.get(key).getShots()));
            p.println(logString("" +soccerHash.get(key).getSaves()));
            p.println(logString("" +soccerHash.get(key).getFouls()));
            p.println(logString("" +soccerHash.get(key).getYellowCards()));
            p.println(logString("" +soccerHash.get(key).getRedCards()));

        }

        return true;
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        return new HashSet<String>();
	}

}
