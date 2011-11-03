package com.bgame

class Unit {
    String name
    User user
    boolean main = false
    MyEnum wtyp = "nah"

    int ferexp = 150
    int nahexp = 150
    int magexp = 150
    int exp = 1
    int ferlvl = 1
    int ferToNext = 100
    int nahlvl = 1
    int nahToNext = 100
    int maglvl = 1
    int magToNext = 100
    
    int str = 10
    int strToNext = 1
    int ges = 10
    int gesToNext = 100
    int inz = 10
    int inzToNext = 100

    int curhp = 999
    int curhppr = 1
    int maxhp = 999
    

    static belongsTo = User
    static constraints = {
        name blank: false, unique: true
    }



    public enum MyEnum {

        nah("Nahkampf"),
        fer("Fernkampf"),
        mag("Magie")

        final String value
        MyEnum (String value) {
            this.value = value
        }
        String toString() { value }
        String getKey() { name() }
    }





    def getlvl(exp){
        def expToNextlvl = 100
        def expTocurrlvl = 0
        def i = 1
        for (i; exp > expToNextlvl;++i){
            expTocurrlvl = expToNextlvl
            expToNextlvl+=25*(i*i)
        }
        ((1-(expToNextlvl-exp)/(expToNextlvl-expTocurrlvl))+(i-2))
    }

    def recalcUnit() {
        def number = getlvl(this.ferexp)
        this.ferlvl = (int)number
        this.ferToNext = (int)(100*(number - this.ferlvl))

        number = getlvl(this.nahexp)
        this.nahlvl = (int)number
        this.nahToNext = (int)(100*(number - this.nahlvl))

        number = getlvl(this.magexp)
        this.maglvl = (int)number
        this.magToNext = (int)(100*(number - this.maglvl))


        //str = 100% Nahkampf + 30% Fernkampf + 0 %Mag
        number = getlvl((this.nahexp*1)+(this.ferexp*0.3)+(this.magexp*0.0))
        this.str =9 + (int)number
        this.strToNext = (int)(100*(number-(int)number))

        //ges = 100% fernkamp + 30% Nahkampf + 0% mag
        number = getlvl((this.ferexp*1)+(this.nahexp*0.3)+(this.magexp*0.0))
        this.ges =9 + (int)number
        this.gesToNext = (int)(100*(number-(int)number))

        //str = 130% Mag + 0% Fernkampf + 0% Nahkampf
        number = getlvl((this.magexp*1.3)+(this.ferexp*0.0)+(this.nahexp*0.0))
        this.inz =9 + (int)number
        this.inzToNext = (int)(100*(number-(int)number))

        this.exp = this.str+this.ges+this.inz*2

        def maxihp = this.str*20+this.ges*10+this.inz*5
        if (this.maxhp != maxihp)this.maxhp = maxihp;
        if (this.curhp > this.maxhp) this.curhp = this.maxhp
        if (this.curhp < 0) this.curhp = 0

        this.curhppr = this.curhp/(this.maxhp /100)
    }
    String toString(){
        return "${name}"
    }
}
