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
registerDecoders(kclient.coder);

connectPromise.then( async (remote, e) =>  {

  if ( remote != null ) {

    // "ugly" example using raw default serialization (not recommended as people really struggle with that)
    const uglydto = {
      "typ": "basics.complexobjects.SampleDTO",
      "obj": {
        "name": "jsname",
        "value": 133,
        "dvalue": 133.13,
        "array": {
          "styp": "array",
          "seq": [ 5, /*length*/ 11, 22, 33, "Vier4", "Fünf5" ]
        },
        "list": {
          "typ": "list",
          "obj": [
            2, // length
            {
              "typ": "basics.complexobjects.SampleDTOEntry",
              "obj": {
                "x": "an X",
                "y": "an Y"
              }
            },
            {
              "typ": "basics.complexobjects.SampleDTOEntry",
              "obj": {
                "x": "another X",
                "y": "another Y"
              }
            }
          ]
        }
      }
    };

    // note: could also use helper above for collection attributes but still ..
    // array: coder.jarray( "array", [ 5, /*length*/,  11, 22, 33, "Vier4", "Fünf5" ] )
    // list: coder.jcoll( "list", [ 2, /*length*/, [ 2, { typ: 'basics.complexobjects.SampleDTOEntry', obj: {..} }, {} ] ] )

    dtoResult = await remote.jsonArgDefaultSerialized(uglydto);
    console.log(dtoResult,e);

  }

});

// as raw json serialization formatting is not very practical, usuallys client install mappers to reformat json coming right out of network
// however this only works in one direction (out of java to javascript)
function registerDecoders(coder) {

  coder.registerDecoder("set",
    obj => {
      obj.splice(0, 1);
      return obj;
    }
  );
  coder.registerDecoder("map",
    obj => {
      const res = {};
      for (let i = 0; i < obj.length; i += 2)
        res[obj[i]] = obj[i + 1];
      res._typ = obj._typ;
      return res;
    }
  );

}