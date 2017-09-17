package com.wilshion.headlinenews.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ChayChan
 * @description: 用户实体类
 * @date 2017/7/9  10:43
 */

public class UserEntity implements Parcelable{
    /**
     * verified_content :
     * avatar_url : http://p3.pstatp.com/thumb/216b000e0abb3ee9cb91
     * user_id : 59834611934
     * name : 电竞手游君
     * follower_count : 0
     * follow : false
     * user_auth_info :
     * user_verified : false
     * description : 游戏 资讯 游戏攻略 你要的这里都有，来这里就对了。
     */

    public String verified_content;
    public String avatar_url;
    public long user_id;
    public String name;
    public int follower_count;
    public boolean follow;
    public String user_auth_info;
    public boolean user_verified;
    public String description;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.verified_content);
        dest.writeString(this.avatar_url);
        dest.writeLong(this.user_id);
        dest.writeString(this.name);
        dest.writeInt(this.follower_count);
        dest.writeByte(this.follow ? (byte) 1 : (byte) 0);
        dest.writeString(this.user_auth_info);
        dest.writeByte(this.user_verified ? (byte) 1 : (byte) 0);
        dest.writeString(this.description);
    }

    public UserEntity() {
    }

    protected UserEntity(Parcel in) {
        this.verified_content = in.readString();
        this.avatar_url = in.readString();
        this.user_id = in.readLong();
        this.name = in.readString();
        this.follower_count = in.readInt();
        this.follow = in.readByte() != 0;
        this.user_auth_info = in.readString();
        this.user_verified = in.readByte() != 0;
        this.description = in.readString();
    }

    public static final Creator<UserEntity> CREATOR = new Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel source) {
            return new UserEntity(source);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };
}
