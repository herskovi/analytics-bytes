if (typeof dwr == 'undefined' || dwr.engine == undefined) throw new Error('You must include DWR engine before including this file');

(function() {
if (dwr.engine._getObject("view") == undefined) {
var p;

p = {};
p._path = '/dwr';





p.getView = function(p0, callback) {
return dwr.engine._execute(p._path, 'view', 'getView', arguments);
};

dwr.engine._setObject("view", p);
}
})();

