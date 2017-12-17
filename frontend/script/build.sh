
rm -rf build/*

mkdir -p build/js
node_modules/.bin/browserify src/index.js -o build/js/spa.js -t [ babelify --presets [ es2015 react ] ]

mkdir -p build/css
node_modules/.bin/lessc src/index.less > build/css/spa.css

cp src/index.html build/index.html

cp build/js/spa.js ../src/main/resources/static/assets/js/spa.js
cp build/css/spa.css ../src/main/resources/static/assets/css/spa.css
cp build/index.html ../src/main/resources/templates/spa.html

