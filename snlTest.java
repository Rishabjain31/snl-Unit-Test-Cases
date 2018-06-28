package com.qainfotech.tap.training.snl.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import com.qainfotech.tap.training.snl.api.InvalidTurnException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qainfotech.tap.training.snl.api.NoUserWithSuchUUIDException;

public class snlTest {
	snlFunc obj;

	@BeforeClass
	 public void start() throws FileNotFoundException, UnsupportedEncodingException, IOException
	  {
		  obj= new snlFunc();
	  }
	
	@Test
	public void moreThanFourPlayers()throws FileNotFoundException,UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException 
	{
		obj.addMoreThan4Players();
	}

	@Test
	public void interruptDuringGame() throws FileNotFoundException, UnsupportedEncodingException, MaxPlayersReachedExeption, IOException, PlayerExistsException, GameInProgressException, InvalidTurnException
	{
		obj.registerduringgame();
	}
	@Test(expectedExceptions=NoUserWithSuchUUIDException.class)
	public void deleteInvalidUser() throws FileNotFoundException, UnsupportedEncodingException, NoUserWithSuchUUIDException
	{
		obj.deleteuserwhoisnotloggedin();
	}
	@Test
	public void verifyUserDeletion() throws IOException, PlayerExistsException,NoUserWithSuchUUIDException
	{
	    boolean true1=obj.checkUserDeletetion();
		Assert.assertTrue(true1);
	}
	
	@Test(expectedExceptions=InvalidTurnException.class)
	public void verifyUserHasWrongTurnOrNot() throws InvalidTurnException, FileNotFoundException, UnsupportedEncodingException,NoUserWithSuchUUIDException 
	{
		obj.wrongTurn();
	}
}
