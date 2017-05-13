# Scala.js with Play

## Slides

### System requirements

- `pandoc`
- `inotify` (for the watch mode)

### Build the slides

~~~ sh
cd slides/
./build.sh
~~~

### Start an HTTP server, watch and re-build the slides on edits

~~~ sh
cd slides/
./build.sh watch
~~~

Then browse [http://localhost:8000](http://localhost:8000).

## Code

### Run the app

~~~ sh
cd code/
sbt run
~~~

Then browse [http://localhost:9000](http://localhost:9000).

