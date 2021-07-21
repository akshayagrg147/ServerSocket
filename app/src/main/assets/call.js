

let peer
function init() {
    peer = new Peer("amm", {
        host: '192.168.43.190',
        port: 9000,
        path: '/socketConnection'
    })

    peer.on('open', () => {
        Android.onPeerConnected()
    })


    return 'helo'
}


