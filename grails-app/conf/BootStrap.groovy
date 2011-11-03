import com.bgame.*
class BootStrap {

    def init = { servletContext ->

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def testUser = new User(username: 'me', enabled: true, password: 'p')
        testUser.save(flush: true)

        def testUser2 = new User(username: 'xian', enabled: true, password: 'p')
        testUser2.save(flush: true)
        testUser2.addToUnits(new Unit(name: 'Xian', main: true,ferexp: 150, nahexp: 9000, magexp : 150,wtyp: 'nah'))
        testUser2.addToUnits(new Unit(name: 'mongo', main: false,ferexp: 6523, nahexp: 150, magexp : 150,wtyp: 'fer'))
        testUser2.unitcount = 2
        testUser2.save(flush: true)
        testUser2.units.each { it.recalcUnit() }

        def testUser3 = new User(username: 'paco', enabled: true, password: 'p')
        testUser3.save(flush: true)
        .save(flush: true)
        testUser3.addToUnits(new Unit(name: 'Paco', main: true,ferexp: 12000, nahexp: 800, magexp : 150,wtyp: 'fer'))
        testUser3.addToUnits(new Unit(name: 'Cora', main: false,ferexp: 150, nahexp: 150, magexp : 13000,wtyp: 'mag'))
        testUser3.unitcount = 2
        testUser3.save(flush: true)
        testUser3.units.each { it.recalcUnit() } 



        def testUser4 = new User(username: 'janis', enabled: true, password: 'p')
        testUser4.save(flush: true)
        testUser4.addToUnits(new Unit(name: 'Janis', main: true,ferexp: 750, nahexp: 9000, magexp : 150,wtyp: 'nah'))
        testUser4.addToUnits(new Unit(name: 'Jizzalot', main: false,ferexp: 8000, nahexp: 150, magexp : 150,wtyp: 'fer'))
        testUser4.unitcount = 2
        testUser4.save(flush: true)
        testUser4.units.each { it.recalcUnit() }


        UserRole.create testUser, adminRole, true
        UserRole.create testUser2, userRole, true
        UserRole.create testUser3, userRole, true
        UserRole.create testUser4, userRole, true

        assert User.count() == 4
        assert Role.count() == 2
        assert UserRole.count() == 4

    }

    def destroy = {
    }

}

