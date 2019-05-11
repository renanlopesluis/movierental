insert into director (id, name) 
	values(null, 'Quentin Tarantino'), 
		(null, 'David Finscher'), 
		(null, 'David Cronenberg'), 
		(null, 'Stanley Kubrick'), 
		(null, 'Tony Kaye');
		
insert into movie (id, title, directorId) 
	values(null, 'Reservoir Dogs', 1), 
		  (null, 'Pulp Fiction', 1), 
		  (null, 'Fight Club', 2),
		  (null, 'Videodrome', 3),
		  (null, 'The Fly', 3),
		  (null, 'Naked Lunch', 3),
		  (null, 'Dead Ringers', 3),
		  (null, 'The Shinning', 4),
		  (null, 'A Clockwork Orange', 4),
		  (null, 'American History X', 5);
		  
insert into moviecopy (id, available, movieId) 
	values(null, true, 1), 
		  (null, true, 1), 
		  (null, true, 2),
		  (null, true, 3),
		  (null, true, 3),
		  (null, true, 3),
		  (null, true, 3),
		  (null, true, 4),
		  (null, true, 4),
		  (null, true, 5);
		  
