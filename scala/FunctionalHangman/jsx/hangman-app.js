
	var HangmanWord = React.createClass({
		render: function() { return (
            <div className="container">
                <section className="header">
                  <h1 className="title">Game of Hangman.</h1>
                  <button className="button-primary">Start Game</button>
                </section>

                <div className="container">
                    <div className="value-props row">
                        <div className="twelve column">
                            <img className="img-80x" src="assets/images/tree7.png"/>
                        </div>
                    </div>
                </div>
            </div>
        );}
    
    });
    React.render(<HangmanWord/>, document.getElementById('gamecontent'));
