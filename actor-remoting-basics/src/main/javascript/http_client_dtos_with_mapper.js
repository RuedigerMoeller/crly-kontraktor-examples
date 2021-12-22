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


//const connectPromise = kclient.connect("http://localhost:7779/ws","WS"); use this for websocket
const connectPromise = kclient.connect("http://localhost:7779/api","HTLP");
const coder = kclient.coder;

connectPromise.then( async (remote, e) =>  {

  if ( remote != null ) {

    const dto = {
      name: "jsname",
      value: 133,
      dvalue: 133.13,
      array: [ 11, 22, 33, "Vier4", "Fünf5" ],
      list: [ { x: 'aa', y: 'bb' }, { x: 'cc', y: 'dd' } ]
    };

    // to make use of object mapping we need to "hide" the dto from default serialization
    // so we need to encode to string manually before and after
    let dtoResult = await remote.jsonArgMapped(JSON.stringify(dto));
    // let dtoResult = await remote.jsonArgAutoMapped(JSON.stringify(dto));

    console.log( JSON.parse(dtoResult),e);

  }

});

