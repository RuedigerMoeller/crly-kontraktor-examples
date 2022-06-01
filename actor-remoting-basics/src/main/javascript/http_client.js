const kontraktorClient = require("kontraktor-client");
const KClient = kontraktorClient.KClient;

const kclient = new KClient();

kclient.listener.log = () => {}; // get rid of kclient's internal logging (open source version)

/**
 * note this example makes use of javascipt "proxy" feature. this way one can call actor API directly.
 * for browser backward compatibility calls to remote need to use ask and tell e.g.
 *
 * 'remote.pingMe("hello")' needs to become then 'remote.ask("pingMe", "hello")'
 *
 */

//const connectPromise = kclient.connect("http://localhost:7779/ws","WS"); // use this for websocket
const connectPromise = kclient.connect("http://localhost:7779/api","HTLP");

connectPromise.then( async (remote, e) =>  {

  if ( remote != null ) {

    let pong = await remote.pingMe("hello");
    console.log(pong);

    remote.timer( 10, (res,err) => {
      if ( res ) console.log(res);
      else if ( err ) console.error(err);
      else console.log("done");
    });

    remote.greet("JavaScript").then( (r,e) => {
      console.log(r);
    });
  }

});

