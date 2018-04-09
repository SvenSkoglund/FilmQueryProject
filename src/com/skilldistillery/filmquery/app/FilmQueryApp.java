package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
	}

	private void test() throws SQLException {
		Film film = db.getFilmById(1);
		System.out.println(film);
		Actor actor = db.getActorById(1);
		System.out.println(actor);
		List<Actor> actors = db.getActorsByFilmId(1);
		System.out.println(actors);
		List<Film> actors2 = db.getFilmsByKeyword("Fanciful");
		System.out.println(actors2);
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);

		input.close();
	}

	private void printMenu() {
		System.out.println("**************************");
		System.out.println("1. Search film by ID");
		System.out.println("2. Search film by keyword");
		System.out.println("0. Exit");
		System.out.println("**************************");
	}

	private void startUserInterface(Scanner input) throws SQLException {
		int entry = -1;
		while (entry != 0) {
			try {
				printMenu();
				entry = input.nextInt();
				if (entry < 0 || entry > 2) {
					throw new InputMismatchException();
				}
				menuSelection(entry, input);
			}
			catch (InputMismatchException e) {
				System.err.print("Invalid Input. Closing program");
				System.exit(1);
			}

		}

	}

	private void menuSelection(int entry, Scanner input) throws SQLException {
		try {
			switch (entry) {
			case 1:
				System.out.println("Enter the film ID");
				int filmID = input.nextInt();
				Film film = db.getFilmById(filmID);
				if (film == null) {
					System.out.println("Film not found");
					break;
				}
				film.printFilm();
				break;
			case 2:
				System.out.println("Enter the keyword to search");
				String keyword = input.next();
				List<Film> films = db.getFilmsByKeyword(keyword);
				if (films == null) {
					System.out.println("Film not found");
					break;
				}
				for (Film filmToPrint: films) {
					filmToPrint.printFilm();
				}
				break;
			case 0:
				System.out.println("Goodbye");
				System.exit(1);
			}
		}
		catch (SQLException e) {
			System.err.println("SQL ERROR. GOODBYE");
			System.exit(1);
		}
	}

}
