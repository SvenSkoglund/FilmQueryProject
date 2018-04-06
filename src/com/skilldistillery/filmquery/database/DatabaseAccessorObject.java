package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	static String user = "student";
	static String pass = "student";
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			System.err.println("Unable to load DB. Exiting program.");
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public Film getFilmById(int filmId) throws SQLException {
		Film film = null;
		String sql = "select f.id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features, l.id from film f join language l on l.id = language_id where f.id = ?";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, filmId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			film = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
					rs.getDouble(7), rs.getInt(8), rs.getDouble(9), rs.getString(10), rs.getString(11),
					rs.getString(12));
		}
		film.setActors(getActorsByFilmId(filmId));
		return film;
	}

	public List<Film> getFilmsByKeyword(String keyword) throws SQLException {
		List<Film> films = new ArrayList<>();
		String sql = "select f.id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features, l.id from film f join language l on l.id = language_id where title like ? or description like ? ";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "%" + keyword + "%");
		ps.setString(2, "%" + keyword + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Film film = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
					rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getDouble(9), rs.getString(10), rs.getString(11),
					rs.getString(12));
			films.add(film);
		}
		// film.setActors(getActorsByFilmId(filmId));
		return films;
	}

	@Override
	public Actor getActorById(int actorId) throws SQLException {
		Actor actor = null;
		String sql = "select * from actor where id = ?";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, actorId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			actor = new Actor(rs.getInt(1), rs.getString(2), rs.getString(3));
		}

		return actor;
	}

	@Override
	public List<Actor> getActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actors = new ArrayList();
		Actor actor = null;
		String sql = "select * from actor a join film_actor fa on fa.actor_id =a.id join film f on f.id = fa.film_id where f.id = ?";
		Connection conn = DriverManager.getConnection(URL, user, pass);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, filmId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			actor = new Actor(rs.getInt(1), rs.getString(2), rs.getString(3));
			actors.add(actor);
		}

		return actors;
	}
}
