package com.bgame
import grails.plugins.springsecurity.Secured
class UnitController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def springSecurityService

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def index = {
        def usrunits = getUserUnits(lookupUser())
        [userunits: usrunits]

        //redirect(action: "userunits", params: params)
    }
    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def enemys = {
        def usrenemys = getEnemyUsers(lookupUser())

        [userenemys: usrenemys]
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def fight = {
        def usrunits = getUserUnits(lookupUser())
        def enemyunits = getUserUnits(User.get(params.enemyid))
        def result = fightsim(usrunits, enemyunits)
        [result: result]
        
    }
    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def heal = {
        def usrunits = getUserUnits(lookupUser())
        healteam(usrunits)
    }

    def healteam (userteam) {
        userteam.each {it.curhp = it.maxhp}


    }
    def fightsim(userteam, enemyteam) {
        def result = "" //rueckgabewert
        def roundcount = 0
        def t1count = 0
        userteam.each {if (it.curhp >0) t1count++}
        def unitcount = t1count
        def t2count = 0
        enemyteam.each {if (it.curhp >0) t2count++}
        def random = new Random()
        if (t2count > unitcount) {
            unitcount = t2count
        }
        def t1exppool = 0
        def t2exppool = 0
        def t1hppool = 0
        def t2hppool = 0
        // nicht von curr hp abhängig machen von max und dann um curr verkleinern!
        // MACHEN!!!!!!
        // nicht von curr hp abhängig machen von max und dann um curr verkleinern!
        userteam.each {
            t1exppool += it.exp
            t1hppool += it.curhp
        }
        def t1dpexp = (int)(t1hppool/t1exppool)

        enemyteam.each {
            t2exppool += it.exp
            t2hppool += it.curhp
        }
        def t2dpexp = (int)(t2hppool/t2exppool)

        //System.out.println("\nt1exppool: "+t1exppool+"\nt1hppool: "+t1hppool+"\n 1exp fuer"+(int)(t1hppool/t1exppool)+" dmg")
        //System.out.println("\nt2exppool: "+t2exppool+"\nt2hppool: "+t2hppool+"\n 1exp fuer"+(int)(t2hppool/t2exppool)+" dmg")
        while (t1count > 0 && t2count > 0){
            roundcount++
            result += "Round."+roundcount+" begins:<br>"

            for (int i = 0; i < unitcount;++i){
                //  System.out.println(roundcount+".Runde "+i+"ter durchgang in For Schleife")
                def rand = -1
                if (userteam.size() >= i+1 && t2count > 0 && t1count > 0){
                    if(userteam[i].curhp >0){
                        //System.out.println(roundcount+".Runde "+i+"ter durchgang in For Schleife. t1count:"+t1count+" t2count:"+t2count)
                        result += userteam[i].name +"["+userteam[i].curhp+"/"+userteam[i].maxhp+ "] attaks enemy "

                        rand = random.nextInt(enemyteam.size())
                        while(enemyteam[rand].curhp <=0){
                            rand = random.nextInt(enemyteam.size())
                            //System.out.println(rand+" in userteam mit "+ userteam.size()+" leuten im team")
                        }

                        result += enemyteam[rand].name +"["+enemyteam[rand].curhp+"/"+enemyteam[rand].maxhp+ "] dealing " + userteam[i].str +" dmg.<br>"
                        if(enemyteam[rand].curhp < userteam[i].str){
                            enemyteam[rand].curhp = 0
                        }else{
                            enemyteam[rand].curhp -= userteam[i].str
                        }
                        enemyteam[rand].save(flush: true)

                        if (t2exppool > 0){
                            //System.out.println("drinne")
                            def expgain = 0
                            //hier anschauen! und das ganze für team2 einfügen :D
                            if (t2dpexp <= userteam[i].str){
                                expgain = (int)(userteam[i].str/t2dpexp)
                            }
                            else{
                                expgain = 1
                            }
                            //System.out.println("davor wtyp: "+userteam[i].wtyp)
                            if(userteam[i].wtyp.value == "Nahkampf"){
                                //System.out.println("nahdrinne")
                                userteam[i].nahexp += expgain
                                result +=  userteam[i].name +" gets "+expgain+" Exp on Nahkampf. <br>"
                            }else if(userteam[i].wtyp.value == "Fernkampf"){
                                //System.out.println("ferdrinne")
                                userteam[i].ferexp += expgain
                                result +=  userteam[i].name +" gets "+expgain+" Exp on Fernkampf. <br>"
                            }else if(userteam[i].wtyp.value == "Magie"){
                                //System.out.println("magdrinne")
                                userteam[i].magexp += expgain
                                result +=  userteam[i].name +" gets "+expgain+" Exp on Magie. <br>"
                            }
                            t2exppool -= expgain
                            userteam[i].recalcUnit()
                            userteam[i].save(flush: true)
                        }

                        if (enemyteam[rand].curhp < 1){
                            result += enemyteam[rand].name +"["+enemyteam[rand].curhp+"/"+enemyteam[rand].maxhp+ "] died.<br><br>"
                            t2count--
                        }
                        else{
                            result += "<br><br>"
                            //  result += enemyteam[rand].name +"["+enemyteam[rand].curhp+"/"+enemyteam[rand].maxhp+ "] survived.<br><br>"
                        }
                    }
                }

                if (enemyteam.size() >= i+1 && t2count > 0 && t1count > 0){
                    if(enemyteam[i].curhp >0){
                        result += "Enemy " + enemyteam[i].name +"["+enemyteam[i].curhp+"/"+enemyteam[i].maxhp+ "] attaks "

                        rand = random.nextInt(userteam.size())
                        while(userteam[rand].curhp <=0){
                            rand = random.nextInt(userteam.size())
                            //System.out.println(rand+" in userteam mit "+ userteam.size()+" leuten im team")
                        }


                        result += userteam[rand].name +"["+userteam[rand].curhp+"/"+userteam[rand].maxhp+ "] dealing " + enemyteam[i].str +" dmg.<br>"
                        if(userteam[rand].curhp < enemyteam[i].str){
                            userteam[rand].curhp = 0
                        }else{
                            userteam[rand].curhp -= enemyteam[i].str
                        }
                        userteam[rand].save(flush: true)





                        if (t1exppool > 0){
                            //System.out.println("drinne")
                            def expgain = 0
                            //hier anschauen! und das ganze für team2 einfügen :D
                            if (t1dpexp <= enemyteam[i].str){
                                expgain = (int)(enemyteam[i].str/t1dpexp)
                            }
                            else{
                                expgain = 1
                            }
                            //System.out.println("davor wtyp: "+userteam[i].wtyp)
                            if(enemyteam[i].wtyp.value == "Nahkampf"){
                                //System.out.println("nahdrinne")
                                enemyteam[i].nahexp += expgain
                                result +=  enemyteam[i].name +" gets "+expgain+" Exp on Nahkampf. <br>"
                            }else if(enemyteam[i].wtyp.value == "Fernkampf"){
                                //System.out.println("ferdrinne")
                                enemyteam[i].ferexp += expgain
                                result +=  enemyteam[i].name +" gets "+expgain+" Exp on Fernkampf. <br>"
                            }else if(enemyteam[i].wtyp.value == "Magie"){
                                //System.out.println("magdrinne")
                                enemyteam[i].magexp += expgain
                                result +=  enemyteam[i].name +" gets "+expgain+" Exp on Magie. <br>"
                            }
                            t1exppool -= expgain
                            enemyteam[i].recalcUnit()
                            enemyteam[i].save(flush: true)
                            //System.out.println(result+"\n\n\n\nDes noch ok\n\n\n")
                        }





                        if (userteam[rand].curhp < 1){
                            result += userteam[rand].name +"["+userteam[rand].curhp+"/"+userteam[rand].maxhp+ "] died.<br><br>"
                            t1count--
                        }
                        else{
                            result += "<br><br>"
                            //     result += userteam[rand].name +"["+userteam[rand].curhp+"/"+userteam[rand].maxhp+ "] survived.<br><br>"
                        }
                    }
                }
                //System.out.println("InForRound."+roundcount+" endss: t1count:"+t1count+" t2count:"+t2count+" unitcount immer"+unitcount+"\n\n")
            }
            //System.out.println("NachForRound."+roundcount+" endss: t1count:"+t1count+" t2count:"+t2count+" unitcount immer"+unitcount+"\n\n")
            //System.out.println(result)
        }
        //System.out.println(result+"\n\n\n\nJOJOJOJOJOJOJOJO\n\n\n")
        if (t2count < 1){
            result += "Attacker "+ userteam[0].user.username+ " wins.<br>"
            def expgain =(int)(t2exppool/t1count)
                userteam.each{
                    if(it.curhp >0){
                        if(it.wtyp.value == "Nahkampf"){
                            it.nahexp += expgain
                        }else if(it.wtyp.value == "Fernkampf"){
                            it.ferexp += expgain
                        }else if(it.wtyp.value == "Magie"){
                            it.magexp += expgain
                        }
                    
                        it.recalcUnit()
                        it.save(flush: true)
                    }
                }
                result += "All alive Units from "+ userteam[0].user.username+ " get "+expgain+"Exp"
            }else if(t1count < 1){
                result += "Enemy "+ enemyteam[0].user.username+ " wins."
                def expgain =(int)(t1exppool/t2count)
                enemyteam.each{
                    if(it.curhp >0){
                        if(it.wtyp.value == "Nahkampf"){
                            it.nahexp += expgain
                        }else if(it.wtyp.value == "Fernkampf"){
                            it.ferexp += expgain
                        }else if(it.wtyp.value == "Magie"){
                            it.magexp += expgain
                        }

                        it.recalcUnit()
                        it.save(flush: true)
                    }
                }
                result += "All alive Units from "+ enemyteam[0].user.username+ " get "+expgain+"Exp"
            }else{
                result += "something is wrong here: t1usercount:"+t1count+" t2enemycount:"+t2count
            }

            //System.out.println(result+"\n\n\n")
            return result
        }
        @Secured(['ROLE_ADMIN','ROLE_USER'])
        def fightquestion = {
            def usr = lookupUser()
            def enemy = User.get(params.enemyid)
            [user: usr,enemy: enemy ]
        }

        @Secured(['ROLE_ADMIN'])
        def list = {
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            [unitInstanceList: Unit.list(params), unitInstanceTotal: Unit.count()]
        }
        @Secured(['ROLE_ADMIN'])
        def create = {
            def unitInstance = new Unit()
            unitInstance.properties = params
            return [unitInstance: unitInstance]
        }
        @Secured(['ROLE_ADMIN','ROLE_USER'])
        def createUnit = {
            def unitInstance = new Unit()
            unitInstance.properties = params
            return [unitInstance: unitInstance]
        }

        def saveunit = {
            def unitInstance = new Unit(params)
            def loggeduser = lookupUser()
            //if(loggeduser.unitcount == 0)
            //überlegen wies beim usererstellen ausgelöst werden kann
            //MACHEN:^abfrage einbauen ob user ne einheit hat wenn nicht erstellen
            //mit username und main = true
            //unitInstance.str = 10;
            //unitInstance.main = false;
            //unitInstance.curhp = 100;
            //unitInstance.maxhp = 100;
        
            unitInstance.user = loggeduser
            unitInstance.recalcUnit()
            if (unitInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'unit.label', default: 'Unit'), unitInstance.id])}"
                redirect(action: "index")
                loggeduser.unitcount ++
            }
            else {
                render(view: "createUnit", model: [unitInstance: unitInstance])
            }
        }



        def save = {
            def unitInstance = new Unit(params)
            if (unitInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'unit.label', default: 'Unit'), unitInstance.id])}"
                redirect(action: "show", id: unitInstance.id)
            }
            else {
                render(view: "create", model: [unitInstance: unitInstance])
            }
        }
        @Secured(['ROLE_ADMIN'])
        def show = {
            def unitInstance = Unit.get(params.id)
            if (!unitInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'unit.label', default: 'Unit'), params.id])}"
                redirect(action: "list")
            }
            else {
                [unitInstance: unitInstance]
            }
        }
        @Secured(['ROLE_ADMIN'])
        def edit = {
            def unitInstance = Unit.get(params.id)
            if (!unitInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'unit.label', default: 'Unit'), params.id])}"
                redirect(action: "list")
            }
            else {
                return [unitInstance: unitInstance]
            }
        }

        def update = {
            def unitInstance = Unit.get(params.id)
            if (unitInstance) {
                if (params.version) {
                    def version = params.version.toLong()
                    if (unitInstance.version > version) {
                    
                        unitInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'unit.label', default: 'Unit')] as Object[], "Another user has updated this Unit while you were editing")
                        render(view: "edit", model: [unitInstance: unitInstance])
                        return
                    }
                }
                unitInstance.properties = params
                if (!unitInstance.hasErrors() && unitInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'unit.label', default: 'Unit'), unitInstance.id])}"
                    redirect(action: "show", id: unitInstance.id)
                }
                else {
                    render(view: "edit", model: [unitInstance: unitInstance])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'unit.label', default: 'Unit'), params.id])}"
                redirect(action: "list")
            }
        }

        def delete = {
            def unitInstance = Unit.get(params.id)
            if (unitInstance) {
                try {
                    unitInstance.delete(flush: true)
                    flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'unit.label', default: 'Unit'), params.id])}"
                    redirect(action: "list")
                }
                catch (org.springframework.dao.DataIntegrityViolationException e) {
                    flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'unit.label', default: 'Unit'), params.id])}"
                    redirect(action: "show", id: params.id)
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'unit.label', default: 'Unit'), params.id])}"
                redirect(action: "list")
            }
        }

        private getUserUnits(User usr){
            def units = Unit.withCriteria {
                user {
                    eq 'username', usr.username
                }
                //maxResults 10
                //order 'asc'
            }
            // def usera = User.findByUsername('xian')
            //System.out.println(usera.username)
            //units.each { println it.unitname }
            //Userunit.each {System.out.println(it.gold) }
            //System.out.println("ergebnis ende")
            units
        }

        private getEnemyUsers(User usr){
            //System.out.println("\n\n\n1geht")
            def enemys = User.withCriteria {
                ne ('username', usr.username)
                gt("unitcount",0)
        
                //maxResults 10
                //order 'asc'
            }
            // def usera = User.findByUsername('xian')
            //System.out.println(usera.username)
            //units.each { println it.unitname }
            //Userunit.each {System.out.println(it.gold) }
            //System.out.println("ergebnis ende")
            enemys
        }


        private lookupUser(){
            User.get(springSecurityService.principal.id)
        }

    }
