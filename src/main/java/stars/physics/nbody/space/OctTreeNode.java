package stars.physics.nbody.space;

import java.util.AbstractList;

import stars.math.Vector3;
import stars.physics.particles.IParticle;

public class OctTreeNode {
    protected OctTreeNode[] _quaduarants;
    AbstractList<IParticle> particles;
    Vector3                 center;
    double                  mass;
    OctTreeNode             parent = null;

    public OctTreeNode(double mass, Vector3 center, OctTreeNode[] quads, AbstractList<IParticle> particles) {
        this.mass = mass;
        this.center = center;
        _quaduarants = quads;
        this.particles = particles;
    }

    public boolean isLeaf() {
        for (OctTreeNode node : _quaduarants) {
            if (node != null) {
                return false;
            }
        }

        return true;
    }

    public Vector3 getCenter() {
        return center;
    }

    public double getMass() {
        return mass;
    }

    public IParticle getNodeParticle() {
        return new OctTreeParticle(getMass(), getCenter());
    }

    public void setParent(OctTreeNode parent) {
        this.parent = parent;
    }

    public OctTreeNode getParent() {
        return parent;
    }

    public AbstractList<IParticle> getParticles() {
        return particles;
    }

    public void setParticles(AbstractList<IParticle> particles) {
        this.particles = particles;
    }

    public OctTreeNode getQuadrant1() {
        return _quaduarants[0];
    }

    public void setQuadrant1(OctTreeNode node) {
        _quaduarants[0] = node;
    }

    public OctTreeNode getQuadrant2() {
        return _quaduarants[1];
    }

    public void setQuadrant2(OctTreeNode node) {
        _quaduarants[1] = node;
    }

    public OctTreeNode getQuadrant3() {
        return _quaduarants[2];
    }

    public void setQuadrant3(OctTreeNode node) {
        _quaduarants[2] = node;
    }

    public OctTreeNode getQuadrant4() {
        return _quaduarants[3];
    }

    public void setQuadrant4(OctTreeNode node) {
        _quaduarants[3] = node;
    }

    public OctTreeNode getQuadrant5() {
        return _quaduarants[4];
    }

    public void setQuadrant5(OctTreeNode node) {
        _quaduarants[4] = node;
    }

    public OctTreeNode getQuadrant6() {
        return _quaduarants[5];
    }

    public void setQuadrant6(OctTreeNode node) {
        _quaduarants[5] = node;
    }

    public OctTreeNode getQuadrant7() {
        return _quaduarants[6];
    }

    public void setQuadrant7(OctTreeNode node) {
        _quaduarants[6] = node;
    }

    public OctTreeNode getQuadrant8() {
        return _quaduarants[7];
    }

    public void setQuadrant8(OctTreeNode node) {
        _quaduarants[7] = node;
    }

}
