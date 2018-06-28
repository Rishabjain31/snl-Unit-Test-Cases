package com.qainfotech.tap.training.snl.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;

public class snlFunc {
	Board board;

	public snlFunc() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		board = new Board();
	}

	public void addMoreThan4Players() {
		int length = 0;
		System.out.println("Number of players: " + length);
		try {
			// board = new Board();
			board.registerPlayer("player1");
			board.registerPlayer("player2");
			board.registerPlayer("player3");
			board.registerPlayer("player4");
			System.out.println("Number of players: " + board.getData().getJSONArray("players").length());
			board.registerPlayer("player5");
			Reporter.log("No exception is thrown on adding more than 4 players.", true);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof MaxPlayersReachedExeption,
					"Assertion Failed: MaxPlayersReachedExeption is not thrown even after registering more than 4 players.");
			Reporter.log("Assertion Passed: MaxPlayersReachedExeption is thrown as more than 4 players are added.",
					true);
		}
	}

	public void registerduringgame()
			throws FileNotFoundException, UnsupportedEncodingException, MaxPlayersReachedExeption, IOException,
			PlayerExistsException, GameInProgressException, InvalidTurnException {
		board.registerPlayer("player1");
		board.registerPlayer("player2");
		board.registerPlayer("player3");
		board.registerPlayer("player4");
		JSONObject data = board.getData();
		UUID player_id = (UUID) (data.getJSONArray("players").getJSONObject(0)).get("uuid");
		board.rollDice(player_id);
	}

	public void deleteuserwhoisnotloggedin()
			throws FileNotFoundException, UnsupportedEncodingException, NoUserWithSuchUUIDException {
		UUID id = UUID.randomUUID();
		board.deletePlayer(id);
	}

	public boolean checkUserDeletetion()
			throws FileNotFoundException, UnsupportedEncodingException, NoUserWithSuchUUIDException {
		addMoreThan4Players();
		JSONObject data = board.getData();
		Boolean status = true;
		UUID player_id = (UUID) (data.getJSONArray("players").getJSONObject(0)).get("uuid");
		board.deletePlayer(player_id);
		data = board.getData();
		for (int i = 0; i < data.getJSONArray("players").length(); i++) {
			UUID id = (UUID) data.getJSONArray("players").getJSONObject(i).get("uuid");
			if (player_id.equals(id))
				status = false;
			else
				status = true;

		}
		return status;
	}

	public boolean userMovedOrNot() throws FileNotFoundException, UnsupportedEncodingException, NoUserWithSuchUUIDException, InvalidTurnException {
		addMoreThan4Players();
		JSONObject data = board.getData();
		Boolean status = true;
		UUID player_id = (UUID) (data.getJSONArray("players").getJSONObject(0)).get("uuid");
		int initial_position = (data.getJSONArray("players").getJSONObject(0)).getInt("position");
		board.rollDice(player_id);
		int final_position = (data.getJSONArray("players").getJSONObject(0)).getInt("position");
		if(initial_position!=final_position)
		{
			status= true;
		}
		else {
			status=false;
		}
		return status;
	}
	public void wrongTurn() throws InvalidTurnException, FileNotFoundException, UnsupportedEncodingException,NoUserWithSuchUUIDException
	{
		addMoreThan4Players();
		JSONObject data = board.getData();	
		UUID player_id = (UUID) (data.getJSONArray("players").getJSONObject(0)).get("uuid");
		board.rollDice(player_id);
  }
}
