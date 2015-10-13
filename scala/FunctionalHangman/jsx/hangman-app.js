
var HangmanWord = React.createClass({
    getInitialState: function() {
        return {word: "",
                message: "",
                numberOfMisses: 0,
                won: false,
                lost: false};
    },
    
    render: function() { return (
        <div className="container">
            <section className="headermain">
                <h1 className="title">Game of Hangman.</h1>
                <button className="button-primary" onClick={this.startGameClicked}>Start Game</button>
            </section>

            <div className="headermain">
                <img className="img-60x" src={"assets/images/tree" + (this.state.numberOfMisses).toString() + ".png"}/>
            </div>
        
            <div className="headermain">
                <span>{this.state.word}</span>
            </div>
                
            <div className="headermain">
                <div class="row">
                    <div class="three columns">
                        <span className="light-keys keys" onClick={this.guessClick}>
                            <button>Q</button> <button>W</button> <button>E</button> <button>R</button> <button>T</button> <button>Y</button> <button>U</button> <button>I</button> <button>O</button> <button>P</button>
                        </span>
                    </div>
                </div>
                <div class="row">
                    <div class="three columns">
                        <span className="light-keys keys"  onClick={this.guessClick}>
                            <button>A</button> <button>S</button> <button>D</button> <button>F</button> <button>G</button> <button>H</button> <button>J</button> <button>K</button> <button>L</button>
                        </span>
                    </div>
                </div>
                <div class="row">
                    <div class="three columns">
                        <span className="light-keys keys" onClick={this.guessClick}>
                            <button>Z</button> <button>X</button> <button>C</button> <button>V</button> <button>B</button> <button>N</button> <button>M</button>
                        </span>
                    </div>
                </div>
            </div>
            
            
        </div>
            
    );},
    
    startGameClicked: function() { return (
        $.ajax({
            url: "/start",
            dataType: 'json',
            cache: false,
            success: function(data) {
                this.setState({word: data["word"],
                               message: data["message"],
                               numberOfMisses: data["misses"],
                               won: data["won"],
                               lost: data["lost"],});
            }.bind(this),
            error: function(xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
      })
    );},

    guessClick: function(event) { 
        if(this.state.word !== "") {
            return (
                $.ajax({
                    url: "/guess/" + event.target.innerHTML,
                    dataType: 'json',
                    cache: false,
                    success: function(data) {
                      this.setState({word: data["word"],
                                     message: data["message"],
                                     numberOfMisses: data["misses"],
                                     won: data["won"],
                                     lost: data["lost"],});
                    }.bind(this),
                    error: function(xhr, status, err) {
                      console.error(this.props.url, status, err.toString());
                    }.bind(this)
                })
        )}
    ;},
        
});

React.render(<HangmanWord imagenum="1"/>, document.getElementById('gamecontent'));
