
<div id="tablehead">
  <h2>${unit.name}</h2>
</div>

<div id="hpbalken">
  <div style="background-color:#440000; z-index:1; width:${unit.curhppr}%;face="Arial">&nbsp</font> </div>
</div>

<div id="hp">
  ${unit.curhp}/${unit.maxhp}
 </div>


<div id="table">
  <table>

    <tr>
      <td>St&auml;rke
        <div id="balken">
          <div style="background-color:#440000; width:${unit.strToNext}%;face="Arial"> ${unit.str}</font></div> </div>
      </td>


      <td>Geschick
        <div id="balken">
          <div style="background-color:#440000; width:${unit.gesToNext}%;face="Arial">${unit.ges}</font></div> </div>
      </td>


      <td>Intelligenz
        <div id="balken">
          <div style="background-color:#440000; width:${unit.inzToNext}%;face="Arial">  ${unit.inz}</font></div> </div>
      </td>

    </tr>

    <tr>
      <td>Nahkampf

        <div id="balken">
          <div style="background-color:#440000; width:${unit.nahToNext}%;face="Arial"> ${unit.nahlvl}</font></div> </div>

      </td>
      <td>Fernkampf

        <div id="balken">
          <div style="background-color:#440000; width:${unit.ferToNext}%;face="Arial">${unit.ferlvl}</font></div> </div>
      </td>
      <td>Magie
        <div id="balken">
          <div style="background-color:#440000; width:${unit.magToNext}%;face="Arial">${unit.maglvl}</font></div> </div>

      </td>
    </tr>
  </table>
</div>
</br>

</br>

